package com.frechsack.dev.observer.core;

/**
 * Marks an Object as Observable. An Observable marks itself as invalid when a change occurs. When it becomes valid again is not defined.
 * The state of this Object can be observed by {@link InvalidationObserver}.
 * <p>
 * Objects that don´t have a state, should treat {@link InvalidationObserver} as some kind of ChangeObserver.
 * <p>
 * When the Object's data changes,
 * they should trigger their InvalidationObservers.
 *
 * @author Frechsack
 * @see InvalidationObserver
 * @see com.frechsack.dev.observer.core.InvalidationObserver.InvalidationEvent
 */
public interface Observable extends Cloneable
{
    /**
     * Adds an {@link InvalidationObserver} to this Observable.
     *
     * @param observer The Observer that should be added.
     * @return Returns true if the observer is successfully added - else false.
     * @see InvalidationObserver
     */
    boolean addObserver(InvalidationObserver observer);

    /**
     * Removes an {@link InvalidationObserver} from this Observable.
     *
     * @param observer The Observer that should be removed.
     * @return Returns true if the observer is successfully removed - else false.
     * @see InvalidationObserver
     */
    boolean removeObserver(InvalidationObserver observer);

    /**
     * Checks if this Observable contains the specified {@link InvalidationObserver}.
     *
     * @param observer The Observer that will be checked.
     * @return Returns true if this Observable contains the specified observer - else false.
     * @see InvalidationObserver
     */
    boolean containsObserver(InvalidationObserver observer);
}
