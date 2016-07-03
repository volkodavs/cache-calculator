package com.sergeyvolkodav.cachecalculator.services;

import com.sergeyvolkodav.cachecalculator.repositories.CalcCacheRepository;
import com.sergeyvolkodav.cachecalculator.utils.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalcularorService implements ICalculator {

    @Autowired
    CalcCacheRepository calcCacheRepository;

    private static final String BASE_PATH = "CALCULATOR";

    public BigDecimal sum(List<BigDecimal> values) {
        if (values.size() == 1) {
            return values.get(0);
        }
        Collections.sort(values);
        return doCalc(values, Operations.ADD);
    }

    public BigDecimal subtract(List<BigDecimal> values) {
        if (values.size() == 1) {
            return values.get(0);
        }
        return doCalc(values, Operations.SUBTRACT);
    }

    public BigDecimal multiply(List<BigDecimal> values) {
        if (values.size() == 1) {
            return values.get(0);
        }
        Collections.sort(values);
        return doCalc(values, Operations.MULTIPLY);
    }

    public BigDecimal divide(List<BigDecimal> values) {
        if (values.size() == 1) {
            return values.get(0);
        }
        return doCalc(values, Operations.DIVIDE);
    }


    private BigDecimal doCalc(List<BigDecimal> values, Operations operations) {
        String sumString = convertToString(values);
        String hashValue = Md5Hash.md5Sum(sumString);
        String redisPath = buildRedisPath(operations.name(), hashValue);
        String cachedResult = calcCacheRepository.getCachedResult(redisPath);
        if (cachedResult == null) {
            BigDecimal calcResult = values.stream().reduce(operations.getOperator()).get();
            calcCacheRepository.saveCachedResult(redisPath, calcResult.toString());
            return calcResult;
        }
        return new BigDecimal(cachedResult);
    }

    private String convertToString(List<?> list) {
        return list.stream().map(e -> e.toString()).collect(Collectors.joining(","));
    }

    private String buildRedisPath(String basePath, String value) {
        return BASE_PATH + ":" + basePath + ":" + value;
    }
}
