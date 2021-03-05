package com.frechsack.dev.observer.core;

/**
 * A SingleChangeObserver observes a {@link ObservableSingle}. When the ObservableSingle detects a change, this Observer will be notified.
 *
 * @param <E> The value type.
 * @author Frechsack
 */
@FunctionalInterface
public interface SingleChangeObserver<E>
{
    /**
     * Called by an {@link ObservableSingle} when it detects a change.
     *
     * @param event An Event that contains information about the Observable.
     */
    void observed(SingleChangeEvent<? extends E> event);

    /**
     * An Event that contains information about the change.
     * <p>
     * It is typical create by the ObservableSingle during the change.
     *
     * @param <E> The value type.
     */
    interface SingleChangeEvent<E> extends InvalidationObserver.InvalidationEvent
    {
        /**
         * Gets the new value of the Observable.
         *
         * @return Returns the new value.
         */
        E get();

        /**
         * Gets the value of the Observable before the change.
         *
         * @return Returns the last value.
         */
        E getLast();

        @Override
        ObservableSingle<? extends E> getSource();
    }
}
