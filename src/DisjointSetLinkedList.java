public class DisjointSetLinkedList implements DisjointSetDataStructure {

    private final int size;

    private class Element {
        int representant;
        int next;
        int length;
        int last;

        public Element(int representant, int next, int length, int last) {
            this.representant = representant;
            this.next = next;
            this.length = length;
            this.last = last;
        }
    }

    private static final int NULL = -1;
    Element[] arr;

    public DisjointSetLinkedList(int size) {
        arr = new Element[size];
        this.size = size;
    }

    @Override
    public void makeSet(int item) {
        arr[item] = new Element(item, NULL, 1, item);
    }

    @Override
    public int findSet(int item) {
        if (item >= size || item < 0) return NULL;

        return arr[item].representant;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        if (itemA >= size || itemB >= size || arr[itemA].representant == arr[itemB].representant) return false;

        if (arr[arr[itemA].representant].length >= arr[arr[itemB].representant].length) {
            // 1. A rep -> B last 2. A last -> first drugiej

            //przypisanie nexta
            arr[arr[itemA].last].next = arr[itemB].representant;
            //1
            arr[arr[itemA].representant].last = arr[itemB].last;

            int nextInt = arr[itemB].representant;
            int iterNumber = arr[arr[itemB].representant].length;

            while (iterNumber > 0) {
                arr[nextInt].representant = arr[itemA].representant;
                nextInt = arr[nextInt].next;
                iterNumber--;
            }
            arr[arr[itemA].representant].length += arr[arr[itemB].representant].length;

        } else {
            return union(itemB, itemA);
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Disjoint sets as linked list:");
        int currentElement;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].representant == i) {
                result.append("\n");
                result.append(i);

                currentElement = arr[i].next;
                for (int j = 0; j < arr[i].length; j++) {
                    if (currentElement != NULL) {
                        result.append(", " + currentElement);
                        currentElement = arr[currentElement].next;
                    }
                }
            }
        }

        return result.toString();
    }
}