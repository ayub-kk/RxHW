import java.util.function.Predicate;

public class FilterObservable<T> extends Observable<T> {
    private final Observable<T> source;
    private final Predicate<T> predicate;

    public FilterObservable(Observable<T> source, Predicate<T> predicate) {
        this.source = source;
        this.predicate = predicate;
        source.subscribe(new Observer<T>() {
            @Override
            public void onNext(T item) {
                if (predicate.test(item)) {
                    emit(item);
                }
            }

            @Override
            public void onError(Throwable t) {
                error(t);
            }

            @Override
            public void onComplete() {
                complete();
            }
        });
    }
}

