import java.util.ArrayList;
import java.util.List;

public class Observable<T> {
    private final List<Observer<? super T>> observers = new ArrayList<>();

    public void subscribe(Observer<? super T> observer) {
        observers.add(observer);
    }

    public void emit(T item) {
        for (Observer<? super T> observer : observers) {
            observer.onNext(item);
        }
    }

    public void complete() {
        for (Observer<? super T> observer : observers) {
            observer.onComplete();
        }
    }

    public void error(Throwable t) {
        for (Observer<? super T> observer : observers) {
            observer.onError(t);
        }
    }

    public static <T> Observable<T> create() {
        return new Observable<>();
    }
}
