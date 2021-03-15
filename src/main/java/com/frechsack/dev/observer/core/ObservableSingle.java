package com.frechsack.dev.observer.core;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.Flow;

/**
 * Marks an Object as Observable. This interface specifies that this {@link Observable} contains a single value.
 * An ObservableSingle should perform it´s invalidation process in the following order:
 * <ol>
 * <li>Reassign the value of this ObservableSingle, if the values are not equal according to {@link java.util.Objects#equals(Object, Object)}.</li>
 * <li>Mark this Observable as invalid an notify {@link InvalidationObserver}.</li>
 * <li>Notify {@link SingleChangeObserver}.</li>
 * </ol>
 * <p>
 * The current value can be observed by a {@link SingleChangeObserver}, when the value changes the ChangeObservers are notified.
 *
 * @param <E> The value type.
 * @author Frechsack
 * @see SingleChangeObserver
 * @see com.frechsack.dev.observer.core.SingleChangeObserver.SingleChangeEvent
 */
public interface ObservableSingle<E> extends Observable, Readable<E>, Serializable, Flow.Publisher<E>
{
    /**
     * Adds an {@link SingleChangeObserver} to this Observable.
     *
     * @param observer The Observer that should be added.
     * @return Returns true if the observer is successfully added - else false.
     * @see SingleChangeObserver
     */
    boolean addObserver(SingleChangeObserver<? super E> observer);

    /**
     * Removes an {@link SingleChangeObserver} from this Observable.
     *
     * @param observer The Observer that should be removed.
     * @return Returns true if the observer is successfully removed - else false.
     * @see SingleChangeObserver
     */
    boolean removeObserver(SingleChangeObserver<? super E> observer);

    /**
     * Checks if this Observable contains the specified {@link SingleChangeObserver}.
     *
     * @param observer The Observer that will be checked.
     * @return Returns true if this Observable contains the specified observer - else false.
     * @see SingleChangeObserver
     */
    boolean containsObserver(SingleChangeObserver<? super E> observer);

    /**
     * Converts this ObservableSingle to an {@link Expression}. The Expression should always contain the same value as this ObservableSingle.
     *
     * @return Returns this ObservableSingle as an Expression.
     * @see Expression
     */
    Expression<E> toExpression();

    @Override
    default void subscribe(Flow.Subscriber<? super E> subscriber)
    {
        Objects.requireNonNull(subscriber, "A null subscriber can not be added.");
        final SingleChangeObserver<E> observer = event ->
        {
            try
            {
                subscriber.onNext(event.get());
            }
            catch (Exception e)
            {
                subscriber.onError(e);
            }
        };
        final Flow.Subscription subscription = new Flow.Subscription()
        {
            @Override
            public void request(long n)
            {
            }

            @Override
            public void cancel()
            {
                removeObserver(observer);
                subscriber.onComplete();
            }
        };
        addObserver(observer);
        subscriber.onSubscribe(subscription);
    }
}
