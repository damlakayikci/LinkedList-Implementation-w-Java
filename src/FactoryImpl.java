import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FactoryImpl implements Factory {
    private Holder first;
    private Holder last;
    private Integer size = 0;

    @Override
    public void addFirst(Product product) {
        Holder holder = new Holder(null, product, null);
        if (size == 0) {
            // If the list is initially empty,
            // the first and last elements should be the same
            first = holder;
            last = first;
        } else {
            // Else, add the holder in the beginning
            holder.setNextHolder(first);
            first.setPreviousHolder(holder);
            // Set the 'first' element to holder
            first = holder;
        }
        size++;
    }

    @Override
    public void addLast(Product product) {
        Holder holder = new Holder(null, product, null);
        if (size == 0) {
            // If the list is initially empty,
            // the first and last elements should be the same
            last = holder;
            first = last;
        } else {
            // Else, add the holder at the end
            last.setNextHolder(holder);
            holder.setPreviousHolder(last);
            // Set the 'last' element to holder
            last = holder;
        }
        size++;
    }

    @Override
    public void add(int index, Product product) throws IndexOutOfBoundsException {
        if ((index <= size) && (index >= 0)) {
            if (index == 0) {
                addFirst(product);
            } else if (index == size) {
                addLast(product);
            } else {
                Holder holder = new Holder(null, product, null);
                // Start from the 1st index, which is the second
                Holder currentHolder = first.getNextHolder();
                for (int i = 1; i < size; i++) {
                    if (i == index) {
                        Holder currentPrev = currentHolder.getPreviousHolder();
                        // If we found the index,
                        // add holder in between next & prev holders
                        currentPrev.setNextHolder(holder);
                        holder.setPreviousHolder(currentPrev);
                        holder.setNextHolder(currentHolder);
                        currentHolder.setPreviousHolder(holder);
                        size++;
                        break;
                    }
                    // Else, move to the next Holder
                    currentHolder = currentHolder.getNextHolder();
                }
            }
        } else {
            throw new IndexOutOfBoundsException();
        }

    }

    @Override
    public Product removeFirst() throws NoSuchElementException {
        if (size != 0) {
            if (size == 1) {
                // If size is one, empty the list
                Product product = first.getProduct();
                // Set the properties of first & last to null
                first.setProduct(null);
                last.setProduct(null);
                size--;
                printProduct(product.getId(), product.getValue());
                return product;
            } else {
                // Assign firstProduct to the value to be returned
                Product firstProduct = first.getProduct();
                // Set the second holder to be the first
                Holder newFirst = first.getNextHolder();
                first = newFirst;
                // First holder isn't linked to anything form front,
                // so that value must be null
                newFirst.setPreviousHolder(null);
                size--;
                printProduct(firstProduct.getId(), firstProduct.getValue());
                return firstProduct;
            }
        } else {
            throw new NoSuchElementException();
        }

    }

    @Override
    public Product removeLast() throws NoSuchElementException {
        if (size != 0) {
            if (size == 1) {
                // If size is one, empty the list
                Product product = first.getProduct();
                // Set the properties of first & last to null
                first.setProduct(null);
                last.setProduct(null);
                size--;
                printProduct(product.getId(), product.getValue());
                return product;
            } else {
                // Assign lastProduct to the value to be returned
                Product lastProduct = last.getProduct();
                // Set the (last-1)th holder to the last
                Holder newLast = last.getPreviousHolder();
                last = newLast;
                // Last holder isn't linked to anything form behind,
                // so that value must be null
                newLast.setNextHolder(null);
                size--;
                printProduct(lastProduct.getId(), lastProduct.getValue());
                return lastProduct;
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Product removeIndex(int index) throws IndexOutOfBoundsException {
        if ((index < size) && (index >= 0)) {
            // Start from the 0th index, which is the first
            Holder currentHolder = first;
            for (int i = 0; i < size; i++) {
                if (i == index) { // If we find the index
                    if (i == 0) {
                        removeFirst();
                    } else if (i == size - 1) {
                        removeLast();
                    } else {
                        // link current Holder's prev & next to each other
                        // and return the Product
                        Product currentProduct = currentHolder.getProduct();
                        Holder currentPrev = currentHolder.getPreviousHolder();
                        Holder currentNext = currentHolder.getNextHolder();
                        currentPrev.setNextHolder(currentNext);
                        currentNext.setPreviousHolder(currentPrev);
                        size--;
                        printProduct(currentProduct.getId(), currentProduct.getValue());
                    }
                    return currentHolder.getProduct();
                }
                // Else, move to the next Holder and increment i by one
                currentHolder = currentHolder.getNextHolder();
            }
        } // Else the index must be greater than size, so out of bonds
        throw new IndexOutOfBoundsException();
    }

    @Override
    public Product removeProduct(int value) throws NoSuchElementException {
        if (size != 0) {
            // Start from the 0th index, which is the first
            Holder currentHolder = first;
            for (int i = 0; i < size; i++) {
                Product currentProduct = currentHolder.getProduct();
                if (currentProduct.getValue() == value) { // If we find the value
                    if (i == 0) {
                        removeFirst();
                    } else if (i == size - 1) {
                        removeLast();
                    } else {
                        // link current Holder's prev & next to each other
                        // and return the Product
                        Holder currentPrev = currentHolder.getPreviousHolder();
                        Holder currentNext = currentHolder.getNextHolder();
                        currentPrev.setNextHolder(currentNext);
                        currentNext.setPreviousHolder(currentPrev);
                        printProduct(currentProduct.getId(), currentProduct.getValue());
                        size--;
                    }
                    return currentHolder.getProduct();
                }
                // Else, move to the next Holder and increment i by one
                currentHolder = currentHolder.getNextHolder();
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public Product find(int id) throws NoSuchElementException {
        if (size != 0) {
            // Start from the 0th index, which is the first
            Holder currentHolder = first;
            for (int i = 0; i < size; i++) {
                Product currentProduct = currentHolder.getProduct();
                if (currentProduct.getId() == id) {
                    // If we found the value, return the Product
                    printProduct(currentProduct.getId(), currentProduct.getValue());
                    return currentHolder.getProduct();
                }
                // Else, move to the next Holder and increment i by one
                currentHolder = currentHolder.getNextHolder();
            }
        } // Else the index must be greater than size, so out of bonds
        throw new NoSuchElementException();
    }

    @Override
    public Product get(int index) throws IndexOutOfBoundsException {
        if ((index < size) && (index >= 0)) {
            // Start from the 0th index, which is the first
            Holder currentHolder = first;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    // If we found the index, return the Product
                    printProduct(currentHolder.getProduct().getId(), currentHolder.getProduct().getValue());
                    return currentHolder.getProduct();
                }
                // Else, move to the next Holder and increment i by one
                currentHolder = currentHolder.getNextHolder();
            }
        }
        throw new IndexOutOfBoundsException();
    }

    // TODO PRINT
    @Override
    public Product update(int id, Integer value) throws NoSuchElementException {
        if (size != 0) {
            // Start from the 0th index, which is the first
            Holder currentHolder = first;
            for (int i = 0; i < size; i++) {
                Product currentProduct = currentHolder.getProduct();
                if (currentProduct.getId() == id) {
                    // If we found the id, return the Product
                    printProduct(id, currentProduct.getValue());
                    currentProduct.setValue(value);
                    return currentHolder.getProduct();
                }
                // Else, move to the next Holder and increment i by one
                currentHolder = currentHolder.getNextHolder();
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int filterDuplicates() {
        int count = 0;
        if (size != 0) {
            ArrayList<Integer> values = new ArrayList<>();
            // Start from the 0th index, which is the first
            Holder currentHolder = first;
            for (int i = 0; i < size; i++) {
                Product currentProduct = currentHolder.getProduct();
                if (!values.contains(currentProduct.getValue())) {
                    // If the value isn't in the arraylist, add it
                    values.add(currentProduct.getValue());
                } else {
                    // It cannot be the first element
                    // If it is the last element
                    if (i == (size - 1)) {
                        //removeLast();
                        // Set the (last-1)th holder to the last
                        Holder newLast = last.getPreviousHolder();
                        last = newLast;
                        // Last holder isn't linked to anything form behind,
                        // so that value must be null
                        newLast.setNextHolder(null);
                    } // If it is in between
                    else {
                        //removeProduct(currentProduct.getValue());
                        Holder currentPrev = currentHolder.getPreviousHolder();
                        Holder currentNext = currentHolder.getNextHolder();
                        currentPrev.setNextHolder(currentNext);
                        currentNext.setPreviousHolder(currentPrev);
                    }
                    count++;
                    size--;
                    i--;
                }
                currentHolder = currentHolder.getNextHolder();
            }
        }
        System.out.println(count);
        return count;
    }

    @Override
    public void reverse() {
        if (size > 1) {
            // Start from the 0th index, which is the first
            Holder currentHolder = first;
            for (int i = 0; i < size; i++) {
                if (i == 0) { // For the first element
                    Holder currentNext = currentHolder.getNextHolder();
                    // Set next holder to null and previous holder to the former next
                    currentHolder.setPreviousHolder(currentNext);
                    currentHolder.setNextHolder(null);
                    // Now the last element should be the former first
                    last = currentHolder;
                } else if (i == size - 1) { // For the last element
                    Holder currentPrevious = currentHolder.getPreviousHolder();
                    // Set next holder to null and previous holder to the former next
                    currentHolder.setNextHolder(currentPrevious);
                    currentHolder.setPreviousHolder(null);
                    // Now the first element should be the former last
                    first = currentHolder;
                } else { // For the elements in between
                    Holder currentPrevious = currentHolder.getPreviousHolder();
                    Holder currentNext = currentHolder.getNextHolder();
                    // Set next holder to the former previous
                    // and previous holder to the former next
                    currentHolder.setPreviousHolder(currentNext);
                    currentHolder.setNextHolder(currentPrevious);
                }
                // Move backwards
                currentHolder = currentHolder.getPreviousHolder();
            }
        }
        print();
    }

    public void print() {
        if (size != 0) {
            Holder currentHolder = first;
            System.out.print('{');
            for (int i = 0; i < size; i++) {
                Product currentProduct = currentHolder.getProduct();
                System.out.print('(' + Integer.toString(currentProduct.getId()) + ", " + currentProduct.getValue() + ')');
                // Print comma if there are more elements
                if (currentHolder.getNextHolder() != null) {
                    System.out.print(',');
                }
                currentHolder = currentHolder.getNextHolder();
            }
            System.out.println('}');
        } else {
            System.out.println("{}");
        }
    }

    public void printProduct(int id, int value) {
        System.out.println('(' + Integer.toString(id) + ", " + value + ')');
    }
}
