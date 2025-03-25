import java.util.function.Function;

public class MapObservable<T, R> extends Observable<R> {
    private final Observable<T> source;
    private final Function<T, R> mapper;

    public MapObservable(Observable<T> source, Function<T, R> mapper) {
        this.source = source;
        this.mapper = mapper;
        source.subscribe(new Observer<T>() {
            @Override
            public void onNext(T item) {
                emit(mapper.apply(item));
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
