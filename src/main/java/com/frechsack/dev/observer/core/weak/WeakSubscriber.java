package com.frechsack.dev.observer.core.weak;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.Flow;

public class WeakSubscriber<E> implements Flow.Subscriber<E>, Weak<Flow.Subscriber<E>>
{
    private final Reference<Flow.Subscriber<E>> subscriberRef;
    private Reference<Flow.Subscription> subscriptionRef;

    public WeakSubscriber(Flow.Subscriber<E> subscriber)
    {
        Objects.requireNonNull(subscriber, "A null subscriber can not be created.");
        this.subscriberRef = new WeakReference<>(subscriber);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription)
    {
        final Flow.Subscriber<E> subscriber = subscriberRef.get();
        subscriptionRef = new WeakReference<>(subscription);
        if (subscriber == null) subscription.cancel();
        else subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(E item)
    {
        final Flow.Subscriber<E> subscriber = subscriberRef.get();
        if (subscriber == null) unsubscribe();
        else subscriber.onNext(item);
    }

    @Override
    public void onError(Throwable throwable)
    {
        final Flow.Subscriber<E> subscriber = subscriberRef.get();
        if (subscriber == null) unsubscribe();
        else subscriber.onError(throwable);
    }

    @Override
    public void onComplete()
    {
        final Flow.Subscriber<E> subscriber = subscriberRef.get();
        if (subscriber == null) unsubscribe();
        else subscriber.onComplete();
    }

    private void unsubscribe()
    {
        final Flow.Subscription subscription = subscriptionRef.get();
        if (subscription != null) subscription.cancel();
    }

    @Override
    public Reference<Flow.Subscriber<E>> getReferentReference()
    {
        return subscriberRef;
    }
}
