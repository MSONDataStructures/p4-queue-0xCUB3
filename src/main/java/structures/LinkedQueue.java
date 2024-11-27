package structures;

/**
 * Implementation of a doubly-linked, generic queue.
 *
 * @param <T> the type of elements stored in the queue
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    private QNode<T> first;
    private QNode<T> last;
    private int count;

    /**
     * Constructs an empty LinkedQueue.
     */
    public LinkedQueue() {
        first = null;
        last = null;
        count = 0;
    }

    /**
     * Adds {@code elem} to the end of this queue.
     *
     * @param elem the element to be added
     * @return the modified queue
     * @throws NullPointerException if {@code elem} is {@code null}
     */
    @Override
    public QueueInterface<T> enqueue(T elem) {
        if (elem == null) {
            throw new NullPointerException("Cannot enqueue null element.");
        }

        QNode<T> newNode = new QNode<>(elem);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrevious(last);
        }
        last = newNode;
        count++;
        return this;
    }

    /**
     * Removes and returns the first element from this queue.
     *
     * @return the first element that was removed
     * @throws IllegalStateException if the queue is empty
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot dequeue from an empty queue.");
        }

        T elem = first.getElement();
        if (first == last) { // aka 1 element in queue
            first = null;
            last = null;
        } else {
            first = first.getNext();
            first.setPrevious(null);
        }
        count--;
        return elem;
    }

    /**
     * Returns the first element in this queue without removing it.
     *
     * @return the first element in the queue
     * @throws IllegalStateException if the queue is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot peek into an empty queue.");
        }
        return first.getElement();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty; {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the size of the queue
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Returns a string representation of the queue in the format:
     * [element1, element2, ..., elementN]
     *
     * @return a string representing the queue
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");

        QNode<T> current = first;
        while (current != null) {
            result.append(current.getElement());
            if (current.getNext() != null) {
                result.append(", ");
            }
            current = current.getNext();
        }

        result.append("]");
        return result.toString();
    }
}