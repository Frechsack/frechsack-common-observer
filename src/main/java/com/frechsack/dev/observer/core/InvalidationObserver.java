package com.frechsack.dev.observer.core;

/**
 * An InvalidationObserver observes an {@link Observable}. When the Observable marks itself as invalid, this Observer will be notified.
 * @author Frechsack
 */
@FunctionalInterface
public interface InvalidationObserver
{
    /**
     * Called by an {@link Observable} during it´s invalidation process.
     * @param event An Event that contains information about the Observable.
     */
    void observed(InvalidationEvent event);

    /**
     * An Event that contains information about the invalidation.
     * <p>
     * Typical it´s created by an {@link Observable} when it marks itself as invalid.
     */
    interface InvalidationEvent
    {
        /**
         * Gets the source of this InvalidationEvent.
         * @return Returns the source.
         */
        Observable getSource();
    }

}
