package com.frechsack.dev.observer.core;

import com.frechsack.dev.observer.simple.SimpleBooleanExpression;
import com.frechsack.dev.observer.simple.SimpleIntegerExpression;
import com.frechsack.dev.observer.simple.SimpleObservables;
import com.frechsack.dev.observer.simple.SimpleStringExpression;

import java.util.List;
import java.util.function.Supplier;

/**
 * Specialised version of {@link Expression}.
 * <p>
 * A StringExpression adds support for common String operations.
 * <p></p>
 *
 * @author Frechsack
 * @see Expression
 */
public interface StringExpression extends Expression<String>
{
    /**
     * Gets a new StringExpression with the result of this Expression´s value appended with the specified String.
     *
     * @param value The String.
     * @return Returns a new StringExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleStringExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see com.frechsack.dev.observer.simple.SimpleStringExpression
     * @see com.frechsack.dev.observer.simple.SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default StringExpression append(String value)
    {
        return new SimpleStringExpression(getString() + value, this)
        {
            @Override
            protected void computeValue()
            {
                setValue(StringExpression.this.getString() + value);
            }
        };
    }

    /**
     * Gets a new StringExpression with the result of this Expression´s value appended with the specified Supplier´s value.
     *
     * @param value The Supplier.
     * @return Returns a new StringExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleStringExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see com.frechsack.dev.observer.simple.SimpleStringExpression
     * @see com.frechsack.dev.observer.simple.SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default StringExpression append(Supplier<String> value)
    {
        return new SimpleStringExpression(getString() + SimpleObservables.saveString(value), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(StringExpression.this.getString() + SimpleObservables.saveString(value));
            }
        };
    }

    /**
     * Gets a new StringExpression with the result of this Expression´s value appended with the specified Supplier´s value.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param value The Supplier.
     * @return Returns a new StringExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleStringExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see com.frechsack.dev.observer.simple.SimpleStringExpression
     * @see com.frechsack.dev.observer.simple.SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default StringExpression append(ObservableSingle<String> value)
    {
        return new SimpleStringExpression(getString() + SimpleObservables.saveString(value), List.of(this, value))
        {
            @Override
            protected void computeValue()
            {
                setValue(StringExpression.this.getString() + SimpleObservables.saveString(value));
            }
        };
    }

    /**
     * Gets a new StringExpression with the result of this Expression´s value {@link String#toLowerCase()}.
     *
     * @return Returns a new StringExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleStringExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleStringExpression
     * @see SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default StringExpression toLowerCaseExpression()
    {
        return new SimpleStringExpression(getString().toLowerCase(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(StringExpression.this.getString().toLowerCase());
            }
        };
    }

    /**
     * Gets a new StringExpression with the result of this Expression´s value {@link String#toUpperCase()}.
     *
     * @return Returns a new StringExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleStringExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleStringExpression
     * @see SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default StringExpression toUpperCaseExpression()
    {
        return new SimpleStringExpression(getString().toUpperCase(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(StringExpression.this.getString().toUpperCase());
            }
        };
    }

    /**
     * Gets a new StringExpression with the result of this Expression´s value {@link String#trim()}.
     *
     * @return Returns a new StringExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleStringExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleStringExpression
     * @see SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default StringExpression trimExpression()
    {
        return new SimpleStringExpression(getString().trim(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(StringExpression.this.getString().trim());
            }
        };
    }

    /**
     * Gets a new {@link BooleanExpression} with the result of this Expression´s value {@link String#isEmpty()}.
     *
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see BooleanExpression
     * @see SimpleBooleanExpression
     * @see SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default BooleanExpression isEmptyExpression()
    {
        return new SimpleBooleanExpression(getString().isEmpty(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(StringExpression.this.getString().isEmpty());
            }
        };
    }

    /**
     * Gets a new {@link NumberExpression} with the result of this Expression´s value {@link String#length()}.
     *
     * @return Returns a new NumberExpression.
     * @implNote The default implementation will return a {@link com.frechsack.dev.observer.simple.SimpleIntegerExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see NumberExpression
     * @see com.frechsack.dev.observer.simple.SimpleIntegerExpression
     * @see SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default NumberExpression<Integer> lengthExpression()
    {
        return new SimpleIntegerExpression(getString().length(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(StringExpression.this.getString().length());
            }
        };
    }

    /**
     * Gets the value of this Expression as a {@link String}. The returned String will never be null.
     *
     * @return Returns this Expression´s value.
     * @implNote The default implementations returns the value supplied by {@link #get()} if it´s not null - a null value will be translated to an empty String.
     */
    default String getString()
    {
        String s = get();
        return s == null ? "" : s;
    }

    /**
     * Returns the value of this Expression. The returned String shouldn´t be null.
     *
     * @return Returns this Expression´s value.
     */
    @Override
    String get();
}
