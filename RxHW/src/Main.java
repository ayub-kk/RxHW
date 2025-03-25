public class Main {
    public static void main(String[] args) {
        testMapOperator();
        testFilterOperator();
        testFlatMapOperator();
    }

    private static void testMapOperator() {
        System.out.println("Testing Map Operator:");

        Observable<Integer> observable = new Observable<>();
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Received: " + item);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Stream completed.");
            }
        });

        MapObservable<Integer, String> mappedObservable = new MapObservable<>(observable, Object::toString);
        mappedObservable.subscribe(new Observer<String>() {
            @Override
            public void onNext(String item) {
                System.out.println("Mapped item: " + item);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Mapped stream completed.");
            }
        });

        mappedObservable.emit(String.valueOf(1));
        mappedObservable.emit(String.valueOf(2));
        mappedObservable.emit(String.valueOf(3));
        mappedObservable.complete();
    }

    private static void testFilterOperator() {
        System.out.println("\nTesting Filter Operator:");

        Observable<Integer> observable = new Observable<>();
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Received: " + item);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Stream completed.");
            }
        });

        FilterObservable<Integer> filteredObservable = new FilterObservable<>(observable, item -> item % 2 == 0);
        filteredObservable.subscribe(new Observer<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Filtered item: " + item);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Filtered stream completed.");
            }
        });

        filteredObservable.emit(1);
        filteredObservable.emit(2);
        filteredObservable.emit(3);
        filteredObservable.emit(4);
        filteredObservable.complete();
    }

    private static void testFlatMapOperator() {
        System.out.println("\nTesting FlatMap Operator:");

        Observable<Integer> observable = new Observable<>();
        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("Received: " + item);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Stream completed.");
            }
        });

        FlatMapObservable<Integer, String> flatMappedObservable = new FlatMapObservable<>(observable, item -> {
            Observable<String> innerObservable = new Observable<>();
            innerObservable.emit(item.toString());
            innerObservable.complete();
            return innerObservable;
        });

        flatMappedObservable.subscribe(new Observer<String>() {
            @Override
            public void onNext(String item) {
                System.out.println("FlatMapped item: " + item);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("FlatMapped stream completed.");
            }
        });

        flatMappedObservable.emit(String.valueOf(1));
        flatMappedObservable.emit(String.valueOf(2));
        flatMappedObservable.emit(String.valueOf(3));
        flatMappedObservable.complete();
    }
}