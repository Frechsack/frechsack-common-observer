package com.frechsack.dev.observer.core;

import com.frechsack.dev.observer.simple.SimpleBooleanExpression;
import com.frechsack.dev.observer.simple.SimpleObservables;

import java.util.List;
import java.util.function.Supplier;

/**
 * Specialised version of {@link Expression}.
 * <p>
 * A BooleanExpression adds support for common boolean operations.
 *
 * @author Frechsack
 * @see Expression
 */
public interface BooleanExpression extends Expression<Boolean>
{
    /**
     * Gets a new BooleanExpression with the result of this Expression´s value and a logical and with the specified boolean.
     *
     * @param value The boolean for the logical and.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleBooleanExpression
     * @see com.frechsack.dev.observer.simple.SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default BooleanExpression and(boolean value)
    {
        return new SimpleBooleanExpression(getBoolean() && value, this)
        {
            @Override
            protected void computeValue()
            {
                setValue(BooleanExpression.this.get() && value);
            }
        };
    }

    /**
     * Gets a new BooleanExpression with the result of this Expression´s value and a logical and with the specified Supplier´s value.
     *
     * @param value The Supplier for the logical and.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleBooleanExpression
     * @see com.frechsack.dev.observer.simple.SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default BooleanExpression and(Supplier<Boolean> value)
    {
        return new SimpleBooleanExpression(getBoolean() && SimpleObservables.saveBoolean(value), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(BooleanExpression.this.getBoolean() && SimpleObservables.saveBoolean(value));
            }
        };
    }

    /**
     * Gets a new BooleanExpression with the result of this Expression´s value and a logical and with the specified Supplier´s value.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param value The Supplier for the logical and.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleBooleanExpression
     * @see com.frechsack.dev.observer.simple.SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default BooleanExpression and(ObservableSingle<Boolean> value)
    {
        return new SimpleBooleanExpression(getBoolean() && SimpleObservables.saveBoolean(value), List.of(this, value))
        {
            @Override
            protected void computeValue()
            {
                setValue(BooleanExpression.this.getBoolean() && SimpleObservables.saveBoolean(value));
            }
        };
    }

    /**
     * Gets a new BooleanExpression with the result of this Expression´s value and a logical or with the specified boolean.
     *
     * @param value The boolean for the logical or.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleBooleanExpression
     * @see com.frechsack.dev.observer.simple.SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default BooleanExpression or(boolean value)
    {
        return new SimpleBooleanExpression(getBoolean() || value, this)
        {
            @Override
            protected void computeValue()
            {
                setValue(BooleanExpression.this.get() || value);
            }
        };
    }

    /**
     * Gets a new BooleanExpression with the result of this Expression´s value and a logical or with the specified Supplier´s value.
     *
     * @param value The Supplier for the logical or.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleBooleanExpression
     * @see com.frechsack.dev.observer.simple.SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default BooleanExpression or(Supplier<Boolean> value)
    {
        return new SimpleBooleanExpression(getBoolean() || SimpleObservables.saveBoolean(value), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(BooleanExpression.this.getBoolean() || SimpleObservables.saveBoolean(value));
            }
        };
    }

    /**
     * Gets a new BooleanExpression with the result of this Expression´s value and a logical or with the specified Supplier´s value.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param value The Supplier for the logical or.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleBooleanExpression
     * @see com.frechsack.dev.observer.simple.SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default BooleanExpression or(ObservableSingle<Boolean> value)
    {
        return new SimpleBooleanExpression(getBoolean() || SimpleObservables.saveBoolean(value), List.of(this, value))
        {
            @Override
            protected void computeValue()
            {
                setValue(BooleanExpression.this.getBoolean() || SimpleObservables.saveBoolean(value));
            }
        };
    }

    /**
     * Gets a new BooleanExpression with the result of this Expression´s value and a logical exclusive or with the specified boolean.
     *
     * @param value The boolean for the logical exclusive or.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleBooleanExpression
     * @see com.frechsack.dev.observer.simple.SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default BooleanExpression xor(boolean value)
    {
        return new SimpleBooleanExpression(getBoolean() != value, this)
        {
            @Override
            protected void computeValue()
            {
                setValue(BooleanExpression.this.getBoolean() != value);
            }
        };
    }

    /**
     * Gets a new BooleanExpression with the result of this Expression´s value and a logical exclusive or with the specified Supplier´s value.
     *
     * @param value The Supplier for the logical exclusive or.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleBooleanExpression
     * @see com.frechsack.dev.observer.simple.SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default BooleanExpression xor(Supplier<Boolean> value)
    {
        return new SimpleBooleanExpression(getBoolean() != SimpleObservables.saveBoolean(value.get()), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(BooleanExpression.this.getBoolean() != SimpleObservables.saveBoolean(value.get()));
            }
        };
    }

    /**
     * Gets a new BooleanExpression with the result of this Expression´s value and a logical exclusive or with the specified Supplier´s value.
     * The {@link ObservableSingle} will be observed. Anytime the observable changes it´s value, the returned Expression will update it´s value.
     *
     * @param value The Supplier for the logical exclusive or.
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see SimpleBooleanExpression
     * @see com.frechsack.dev.observer.simple.SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default BooleanExpression xor(ObservableSingle<Boolean> value)
    {
        return new SimpleBooleanExpression(getBoolean() != SimpleObservables.saveBoolean(value.get()), List.of(this, value))
        {
            @Override
            protected void computeValue()
            {
                setValue(BooleanExpression.this.getBoolean() != SimpleObservables.saveBoolean(value.get()));
            }
        };
    }

    /**
     * Gets a new BooleanExpression with the opposite of this Expression´s value.
     *
     * @return Returns a new BooleanExpression.
     * @implNote The default implementation will return a {@link SimpleBooleanExpression} with the default {@link com.frechsack.dev.observer.simple.EventHandler}.
     * @see BooleanExpression
     * @see SimpleBooleanExpression
     * @see SimpleObservables
     * @see com.frechsack.dev.observer.simple.EventHandler
     */
    default BooleanExpression invert()
    {
        return new SimpleBooleanExpression(!getBoolean(), this)
        {
            @Override
            protected void computeValue()
            {
                setValue(!BooleanExpression.this.getBoolean());
            }
        };
    }

    /**
     * Gets the value of this Expression as a primitive boolean.
     *
     * @return Returns this Expression´s value.
     */
    boolean getBoolean();

    /**
     * Returns the value of this Expression. The returned Boolean shouldn´t be null.
     * @return Returns this Expression´s The value.
     */
    @Override
    Boolean get();
}
