import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ObservableTest {

    @Test
    void testMapOperator() {
        Observable<Integer> observable = Observable.create();
        MapObservable<Integer, String> mapped = new MapObservable<>(observable, Object::toString);

        mapped.subscribe(new Observer<String>() {
            @Override
            public void onNext(String item) {
                assertEquals("1", item);
            }

            @Override
            public void onError(Throwable t) {}

            @Override
            public void onComplete() {}
        });

        observable.emit(1);
        observable.complete();
    }

    @Test
    void testFilterOperator() {
        Observable<Integer> observable = Observable.create();
        FilterObservable<Integer> filtered = new FilterObservable<>(observable, item -> item % 2 == 0);

        List<Integer> receivedItems = new ArrayList<>();

        filtered.subscribe(new Observer<Integer>() {
            @Override
            public void onNext(Integer item) {
                receivedItems.add(item);
            }

            @Override
            public void onError(Throwable t) {}

            @Override
            public void onComplete() {}
        });

        observable.emit(1);
        observable.emit(2);
        observable.emit(3);
        observable.emit(4);
        observable.complete();

        assertEquals(Arrays.asList(2, 4), receivedItems);
    }

    @Test
    void testFlatMapOperator() {
        Observable<Integer> observable = Observable.create();
        FlatMapObservable<Integer, String> flatMapped = new FlatMapObservable<>(observable, item -> {
            Observable<String> innerObservable = Observable.create();
            innerObservable.emit("Value: " + item);
            innerObservable.complete();
            return innerObservable;
        });

        List<String> receivedItems = new ArrayList<>();

        flatMapped.subscribe(new Observer<String>() {
            @Override
            public void onNext(String item) {
                receivedItems.add(item);
            }

            @Override
            public void onError(Throwable t) {}

            @Override
            public void onComplete() {}
        });

        observable.emit(1);
        observable.emit(2);
        observable.emit(3);
        observable.complete();

    }
}