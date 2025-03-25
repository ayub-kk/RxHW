import java.util.function.Function;

public class FlatMapObservable<T, R> extends Observable<R> {
    private final Observable<T> source;
    private final Function<T, Observable<R>> mapper;

    public FlatMapObservable(Observable<T> source, Function<T, Observable<R>> mapper) {
        this.source = source;
        this.mapper = mapper;
        source.subscribe(new Observer<T>() {
            @Override
            public void onNext(T item) {
                Observable<R> newObservable = mapper.apply(item);
                newObservable.subscribe(new Observer<R>() {
                    @Override
                    public void onNext(R innerItem) {
                        emit(innerItem);
                    }

                    @Override
                    public void onError(Throwable t) {
                        error(t);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
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
