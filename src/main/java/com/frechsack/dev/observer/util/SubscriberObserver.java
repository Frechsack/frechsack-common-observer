package com.frechsack.dev.observer.util;

import com.frechsack.dev.observer.core.SingleChangeObserver;

import java.util.Objects;
import java.util.concurrent.Flow;

public class SubscriberObserver<E> implements SingleChangeObserver<E>, Flow.Subscriber<E>
{
    private final Flow.Subscriber<? super E> subscriber;

    public SubscriberObserver(Flow.Subscriber<? super E> subscriber)
    {
        Objects.requireNonNull(subscriber, "A null subscriber can not be added.");
        this.subscriber = subscriber;
    }

    @Override
    public final void observed(SingleChangeEvent<? extends E> event)
    {
        try
        {
            subscriber.onNext(event.get());
        }
        catch (Exception e)
        {
            subscriber.onError(e);
        }
    }

    @Override
    public final void onSubscribe(Flow.Subscription subscription)
    {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public final void onNext(E item)
    {
        subscriber.onNext(item);
    }

    @Override
    public final void onError(Throwable throwable)
    {
        subscriber.onError(throwable);
    }

    @Override
    public final void onComplete()
    {
        subscriber.onComplete();
    }
}
