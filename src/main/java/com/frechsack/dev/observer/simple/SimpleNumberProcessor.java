package com.frechsack.dev.observer.simple;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class SimpleNumberProcessor
{
    protected static RuntimeException divisionByZeroException()
    {
        return new ArithmeticException("Division by zero is not allowed.");
    }

    protected static boolean isZero(Number n)
    {
        if (n == null) return true;
        if (isBigInteger(n)) return n.equals(BigInteger.ZERO);
        if (isBigDecimal(n)) return n.equals(BigDecimal.ZERO);
        if (isDouble(n)) return n.doubleValue() == 0;
        if (isLong(n)) return n.longValue() == 0;
        if (isFloat(n)) return n.floatValue() == 0;
        if (isInteger(n)) return n.intValue() == 0;
        if (isShort(n)) return n.shortValue() == 0;
        if (isByte(n)) return n.byteValue() == 0;
        return false;
    }

    protected static Number powOperation(Number a, Number b)
    {
        if (a == null) return 0;
        // Zero Check
        if (isZero(a)) return 0;
        if (isBigDecimal(a)) return ((BigDecimal) a).pow(isZero(b) ? 0 : b.intValue());
        else if (isBigInteger(a)) return ((BigInteger) a).pow(isZero(b) ? 0 : b.intValue());
        else return Math.pow(a.doubleValue(), isZero(b) ? 0d : b.doubleValue());
    }

    protected static Number divideOperation(Number a, Number b)
    {
        if (a == null)
        {
            if (b == null) return 0;
            else return b;
        }
        else if (b == null) return a;
        // Zero Check
        if (isZero(a) || isZero(b)) return 0;

        if (isBigDecimal(a))
        {
            if (isBigDecimal(b)) return ((BigDecimal) a).divide((BigDecimal) b, RoundingMode.HALF_UP);
            if (isBigInteger(b)) return ((BigDecimal) a).divide(BigDecimal.valueOf(b.longValue()), RoundingMode.HALF_UP);
            if (isDouble(b) || isFloat(b)) return ((BigDecimal) a).divide(BigDecimal.valueOf(b.floatValue()), RoundingMode.HALF_UP);
            if (isLong(b) || isByte(b) || isInteger(b) || isShort(b)) return ((BigDecimal) a).divide(BigDecimal.valueOf(b.longValue()), RoundingMode.HALF_UP);
            else return a;
        }
        else if (isBigInteger(a))
        {
            if (isBigDecimal(b)) return ((BigInteger) a).divide(BigInteger.valueOf(b.longValue()));
            if (isBigInteger(b)) return ((BigInteger) a).divide((BigInteger) b);
            if (isDouble(b) || isFloat(b)) return BigDecimal.valueOf(a.longValue()).divide(BigDecimal.valueOf(b.doubleValue()), RoundingMode.HALF_UP);
            if (isLong(b) || isByte(b) || isInteger(b) || isShort(b)) return ((BigInteger) a).divide(BigInteger.valueOf(b.longValue()));
            else return a;
        }
        else if (isDouble(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.doubleValue()).divide((BigDecimal) b, RoundingMode.HALF_UP);
            if (isBigInteger(b)) return BigDecimal.valueOf(a.doubleValue()).divide(BigDecimal.valueOf(b.longValue()), RoundingMode.HALF_UP);
            else return a.doubleValue() / b.doubleValue();
        }
        else if (isLong(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).divide((BigDecimal) b, RoundingMode.HALF_UP);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).divide((BigInteger) b);
            if (isDouble(b) || isFloat(b)) return a.doubleValue() / b.doubleValue();
            else return a.longValue() / b.longValue();
        }
        else if (isFloat(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).divide((BigDecimal) b, RoundingMode.HALF_UP);
            if (isBigInteger(b)) return BigDecimal.valueOf(a.longValue()).divide(BigDecimal.valueOf(b.longValue()), RoundingMode.HALF_UP);
            else return a.floatValue() / b.floatValue();
        }
        else if (isInteger(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).divide((BigDecimal) b, RoundingMode.HALF_UP);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).divide(BigInteger.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return a.doubleValue() / b.doubleValue();
            else return a.intValue() / b.intValue();
        }
        else if (isShort(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).divide((BigDecimal) b, RoundingMode.HALF_UP);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).divide(BigInteger.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return a.doubleValue() / b.doubleValue();
            else return a.shortValue() / b.shortValue();
        }
        else if (isByte(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).divide((BigDecimal) b, RoundingMode.HALF_UP);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).divide(BigInteger.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return a.doubleValue() / b.doubleValue();
            else return a.byteValue() / b.byteValue();
        }
        return 0;
    }

    protected static Number moduloOperation(Number a, Number b)
    {
        if (isZero(a)) return 0;
        if (isZero(b)) return a;
        if (isBigDecimal(a))
        {
            if (isBigDecimal(b)) return ((BigDecimal) a).remainder((BigDecimal) b);
            if (isBigInteger(b)) return ((BigDecimal) a).remainder(BigDecimal.valueOf(b.longValue()));
            else return ((BigDecimal) a).remainder(BigDecimal.valueOf(b.doubleValue()));
        }
        else if (isBigInteger(a))
        {
            if (isBigDecimal(b)) return (new BigDecimal((BigInteger) a)).remainder((BigDecimal) b);
            if (isBigInteger(b)) return ((BigInteger) a).remainder((BigInteger) b);
            if (isFloat(b) || isDouble(b)) return new BigDecimal((BigInteger) a).remainder(BigDecimal.valueOf(b.doubleValue()));
            else return ((BigInteger) a).remainder(BigInteger.valueOf(b.longValue()));
        }
        else if (isDouble(a) || isFloat(a))
        {
            if (isBigInteger(b)) return BigDecimal.valueOf(a.doubleValue()).remainder(new BigDecimal((BigInteger) b));
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.doubleValue()).remainder((BigDecimal) b);
            else return a.doubleValue() % b.doubleValue();
        }
        else
        {
            if (isBigInteger(b)) return BigDecimal.valueOf(a.longValue()).remainder(new BigDecimal((BigInteger) b));
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).remainder((BigDecimal) b);
            return a.doubleValue() % b.doubleValue();
        }
    }

    protected static Number multiplyOperation(Number a, Number b)
    {
        if (a == null)
        {
            if (b == null) return 0;
            else return b;
        }
        else if (b == null) return a;

        // Zero Check
        if (isZero(a) || isZero(b)) return 0;

        if (isBigDecimal(a))
        {
            if (isBigDecimal(b)) return ((BigDecimal) a).multiply((BigDecimal) b);
            if (isBigInteger(b)) return ((BigDecimal) a).multiply(BigDecimal.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return ((BigDecimal) a).multiply(BigDecimal.valueOf(b.floatValue()));
            if (isLong(b) || isByte(b) || isInteger(b) || isShort(b)) return ((BigDecimal) a).multiply(BigDecimal.valueOf(b.longValue()));
            else return a;
        }
        else if (isBigInteger(a))
        {
            if (isBigDecimal(b)) return ((BigInteger) a).multiply(BigInteger.valueOf(b.longValue()));
            if (isBigInteger(b)) return ((BigInteger) a).multiply((BigInteger) b);
            if (isDouble(b) || isFloat(b)) return BigDecimal.valueOf(a.longValue()).multiply(BigDecimal.valueOf(b.doubleValue()));
            if (isLong(b) || isByte(b) || isInteger(b) || isShort(b)) return ((BigInteger) a).multiply(BigInteger.valueOf(b.longValue()));
            else return a;
        }
        else if (isDouble(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.doubleValue()).multiply((BigDecimal) b);
            if (isBigInteger(b)) return BigDecimal.valueOf(a.doubleValue()).multiply(BigDecimal.valueOf(b.longValue()));
            else return a.doubleValue() * b.doubleValue();
        }
        else if (isLong(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).multiply((BigDecimal) b);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).multiply((BigInteger) b);
            if (isDouble(b) || isFloat(b)) return a.longValue() * b.doubleValue();
            else return a.longValue() * b.longValue();
        }
        else if (isFloat(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).multiply((BigDecimal) b);
            if (isBigInteger(b)) return BigDecimal.valueOf(a.longValue()).multiply(BigDecimal.valueOf(b.longValue()));
            else return a.floatValue() * b.floatValue();
        }
        else if (isInteger(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).multiply((BigDecimal) b);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).multiply(BigInteger.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return a.intValue() * b.doubleValue();
            else return a.intValue() * b.intValue();
        }
        else if (isShort(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).multiply((BigDecimal) b);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).multiply(BigInteger.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return a.shortValue() * b.doubleValue();
            else return a.shortValue() * b.shortValue();
        }
        else if (isByte(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).multiply((BigDecimal) b);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).multiply(BigInteger.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return a.byteValue() * b.doubleValue();
            else return a.byteValue() * b.byteValue();
        }
        return 0;
    }

    protected static Number subtractOperation(Number a, Number b)
    {
        if (a == null)
        {
            if (b == null) return 0;
            else return b;
        }
        else if (b == null) return a;

        if (isZero(b)) return a;

        if (isBigDecimal(a))
        {
            if (isBigDecimal(b)) return ((BigDecimal) a).subtract((BigDecimal) b);
            if (isBigInteger(b)) return ((BigDecimal) a).subtract(BigDecimal.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return ((BigDecimal) a).subtract(BigDecimal.valueOf(b.floatValue()));
            if (isLong(b) || isByte(b) || isInteger(b) || isShort(b)) return ((BigDecimal) a).subtract(BigDecimal.valueOf(b.longValue()));
            else return a;
        }
        else if (isBigInteger(a))
        {
            if (isBigDecimal(b)) return ((BigInteger) a).subtract(BigInteger.valueOf(b.longValue()));
            if (isBigInteger(b)) return ((BigInteger) a).subtract((BigInteger) b);
            if (isDouble(b) || isFloat(b)) return BigDecimal.valueOf(a.longValue()).subtract(BigDecimal.valueOf(b.doubleValue()));
            if (isLong(b) || isByte(b) || isInteger(b) || isShort(b)) return ((BigInteger) a).subtract(BigInteger.valueOf(b.longValue()));
            else return a;
        }
        else if (isDouble(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.doubleValue()).subtract((BigDecimal) b);
            if (isBigInteger(b)) return BigDecimal.valueOf(a.doubleValue()).subtract(BigDecimal.valueOf(b.longValue()));
            else return a.doubleValue() - b.doubleValue();
        }
        else if (isLong(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).subtract((BigDecimal) b);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).subtract((BigInteger) b);
            if (isDouble(b) || isFloat(b)) return a.longValue() - b.doubleValue();
            else return a.longValue() - b.longValue();
        }
        else if (isFloat(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).subtract((BigDecimal) b);
            if (isBigInteger(b)) return BigDecimal.valueOf(a.longValue()).subtract(BigDecimal.valueOf(b.longValue()));
            else return a.floatValue() - b.floatValue();
        }
        else if (isInteger(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).subtract((BigDecimal) b);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).subtract(BigInteger.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return a.intValue() - b.doubleValue();
            else return a.intValue() - b.intValue();
        }
        else if (isShort(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).subtract((BigDecimal) b);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).subtract(BigInteger.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return a.shortValue() - b.doubleValue();
            else return a.shortValue() - b.shortValue();
        }
        else if (isByte(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).subtract((BigDecimal) b);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).subtract(BigInteger.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return a.byteValue() - b.doubleValue();
            else return a.byteValue() - b.byteValue();
        }
        return 0;
    }

    protected static Number addOperation(Number a, Number b)
    {
        if (a == null)
        {
            if (b == null) return 0;
            else return b;
        }
        else if (b == null) return a;

        if (isZero(b)) return a;

        if (isBigDecimal(a))
        {
            if (isBigDecimal(b)) return ((BigDecimal) a).add((BigDecimal) b);
            if (isBigInteger(b)) return ((BigDecimal) a).add(BigDecimal.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return ((BigDecimal) a).add(BigDecimal.valueOf(b.floatValue()));
            if (isLong(b) || isByte(b) || isInteger(b) || isShort(b)) return ((BigDecimal) a).add(BigDecimal.valueOf(b.longValue()));
            else return a;
        }
        else if (isBigInteger(a))
        {
            if (isBigDecimal(b)) return ((BigInteger) a).add(BigInteger.valueOf(b.longValue()));
            if (isBigInteger(b)) return ((BigInteger) a).add((BigInteger) b);
            if (isDouble(b) || isFloat(b)) return BigDecimal.valueOf(a.longValue()).add(BigDecimal.valueOf(b.doubleValue()));
            if (isLong(b) || isByte(b) || isInteger(b) || isShort(b)) return ((BigInteger) a).add(BigInteger.valueOf(b.longValue()));
            else return a;
        }
        else if (isDouble(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.doubleValue()).add((BigDecimal) b);
            if (isBigInteger(b)) return BigDecimal.valueOf(a.doubleValue()).add(BigDecimal.valueOf(b.longValue()));
            else return a.doubleValue() + b.doubleValue();
        }
        else if (isLong(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).add((BigDecimal) b);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).add((BigInteger) b);
            if (isDouble(b) || isFloat(b)) return a.longValue() + b.doubleValue();
            else return a.longValue() + b.longValue();
        }
        else if (isFloat(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).add((BigDecimal) b);
            if (isBigInteger(b)) return BigDecimal.valueOf(a.longValue()).add(BigDecimal.valueOf(b.longValue()));
            else return a.floatValue() + b.floatValue();
        }
        else if (isInteger(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).add((BigDecimal) b);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).add(BigInteger.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return a.intValue() + b.doubleValue();
            else return a.intValue() + b.intValue();
        }
        else if (isShort(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).add((BigDecimal) b);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).add(BigInteger.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return a.shortValue() + b.doubleValue();
            else return a.shortValue() + b.shortValue();
        }
        else if (isByte(a))
        {
            if (isBigDecimal(b)) return BigDecimal.valueOf(a.longValue()).add((BigDecimal) b);
            if (isBigInteger(b)) return BigInteger.valueOf(a.longValue()).add(BigInteger.valueOf(b.longValue()));
            if (isDouble(b) || isFloat(b)) return a.byteValue() + b.doubleValue();
            else return a.byteValue() + b.byteValue();
        } return 0;
    }

    protected static Number invertOperation(Number a)
    {
        if (isZero(a)) return 0;
        if (isBigDecimal(a)) return ((BigDecimal) a).multiply(BigDecimal.valueOf(-1));
        if (isBigInteger(a)) return ((BigInteger) a).multiply(BigInteger.valueOf(-1));
        if (isDouble(a)) return a.doubleValue() * -1;
        if (isLong(a)) return a.longValue() * -1;
        if (isFloat(a)) return a.floatValue() * -1;
        if (isInteger(a)) return a.intValue() * -1;
        if (isByte(a)) return a.byteValue() * -1;
        if (isShort(a)) return a.shortValue() * -1;
        return 0;
    }

    protected static Number sqrtOperation(Number a)
    {
        if (isZero(a)) return 0;
        if (isBigInteger(a))
        {
            BigInteger bigInteger = (BigInteger) a;
            if (bigInteger.signum() < 0) return 0;
            return bigInteger.sqrt();
        }
        if (isBigDecimal(a))
        {
            BigDecimal bigDecimal = (BigDecimal) a;
            try
            {
                return bigDecimal.sqrt(MathContext.DECIMAL128);
            }
            catch (ArithmeticException e)
            {
                return 0;
            }
        }
        return Math.sqrt(a.doubleValue());
    }

    protected static Number positiveOperation(Number a)
    {
        if (isBigInteger(a))
        {
            BigInteger bigInteger = (BigInteger) a;
            if (bigInteger.signum() < 0) return bigInteger.negate();
            return bigInteger;
        }
        if (isBigDecimal(a))
        {
            BigDecimal bigDecimal = (BigDecimal) a;
            if (bigDecimal.signum() < 0) return bigDecimal.negate();
            return bigDecimal;
        }
        if (isDouble(a)) return a.doubleValue() < 0 ? a.doubleValue() * -1 : 0;
        if (isLong(a)) return a.longValue() < 0 ? a.longValue() * -1 : 0;
        if (isFloat(a)) return a.floatValue() < 0 ? a.floatValue() * -1 : 0;
        if (isInteger(a)) return a.intValue() < 0 ? a.intValue() * -1 : 0;
        if (isByte(a)) return a.byteValue() < 0 ? a.byteValue() * -1 : 0;
        if (isShort(a)) return a.shortValue() < 0 ? a.shortValue() * -1 : 0;
        return 0;
    }

    protected static Number negativeOperation(Number a)
    {
        if (isBigInteger(a))
        {
            BigInteger bigInteger = (BigInteger) a;
            if (bigInteger.signum() > 0) return bigInteger.negate();
            return bigInteger;
        }
        if (isBigDecimal(a))
        {
            BigDecimal bigDecimal = (BigDecimal) a;
            if (bigDecimal.signum() > 0) return bigDecimal.negate();
            return bigDecimal;
        }
        if (isDouble(a)) return a.doubleValue() > 0 ? a.doubleValue() * -1 : 0;
        if (isLong(a)) return a.longValue() > 0 ? a.longValue() * -1 : 0;
        if (isFloat(a)) return a.floatValue() > 0 ? a.floatValue() * -1 : 0;
        if (isInteger(a)) return a.intValue() > 0 ? a.intValue() * -1 : 0;
        if (isByte(a)) return a.byteValue() > 0 ? a.byteValue() * -1 : 0;
        if (isShort(a)) return a.shortValue() > 0 ? a.shortValue() * -1 : 0;
        return 0;
    }

    protected static int compareOperation(Number base, Number value)
    {
        if (base == null) base = 0;
        if (value == null) value = 0;
        if (!(base instanceof Comparable<?>)) return 0;
        if (isBigDecimal(base))
        {
            if (isBigDecimal(value)) return ((BigDecimal) base).compareTo((BigDecimal) value);
            if (isBigInteger(value)) return ((BigDecimal) base).compareTo(new BigDecimal((BigInteger) value));
            if (isDouble(value) || isFloat(value)) return ((BigDecimal) base).compareTo(BigDecimal.valueOf(value.doubleValue()));
            return ((BigDecimal) base).compareTo(new BigDecimal(value.longValue()));
        }
        if (isBigInteger(base))
        {
            if (isBigDecimal(value)) return new BigDecimal((BigInteger) base).compareTo((BigDecimal) value);
            if (isBigInteger(value)) return ((BigInteger) base).compareTo((BigInteger) value);
            if (isDouble(value) || isFloat(value)) return new BigDecimal((BigInteger) base).compareTo(BigDecimal.valueOf(value.doubleValue()));
            return ((BigInteger) base).compareTo(BigInteger.valueOf(value.longValue()));
        }
        if (isDouble(base)) return ((Double) base).compareTo(value.doubleValue());
        if (isLong(base)) return ((Long) base).compareTo(value.longValue());
        if (isFloat(base)) return ((Float) base).compareTo(value.floatValue());
        if (isInteger(base)) return ((Integer) base).compareTo(value.intValue());
        if (isShort(base)) return ((Short) base).compareTo(value.shortValue());
        if (isByte(base)) return ((Byte) base).compareTo(value.byteValue());
        return 0;
    }

    protected static boolean isBiggerThanOperation(Number base, Number value)
    {
        return compareOperation(base, value) == 1;
    }

    protected static boolean isBiggerThanOrEqualToOperation(Number base, Number value)
    {
        int op = compareOperation(base, value);
        return op == 0 || op == 1;
    }

    protected static boolean isLessThanOperation(Number base, Number value)
    {
        return compareOperation(base, value) == -1;
    }

    protected static boolean isLessThanOrEqualToOperation(Number base, Number value)
    {
        int op = compareOperation(base, value);
        return op == 0 || op == -1;
    }

    protected static boolean isEqualToOperation(Number base, Number value)
    {
        return compareOperation(base, value) == 0;
    }

    protected static boolean isBigDecimal(Number n)
    {
        return n instanceof BigDecimal;
    }

    protected static boolean isBigInteger(Number n)
    {
        return n instanceof BigInteger;
    }

    protected static boolean isLong(Number n)
    {
        return n != null && n.getClass() == Long.class;
    }

    static boolean isDouble(Number n)
    {
        return n != null && n.getClass() == Double.class;
    }

    protected static boolean isInteger(Number n)
    {
        return n != null && n.getClass() == Integer.class;
    }

    protected static boolean isFloat(Number n)
    {
        return n != null && n.getClass() == Float.class;
    }

    protected static boolean isByte(Number n)
    {
        return n != null && n.getClass() == Byte.class;
    }

    protected static boolean isShort(Number n)
    {
        return n != null && n.getClass() == Short.class;
    }


}
