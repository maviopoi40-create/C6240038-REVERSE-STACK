public class PRACTICE {
    static class ArrayStack {
        private int[] data;
        private int top;   // index of next free slot == size

        public ArrayStack(int initialCapacity) {
            data = new int[initialCapacity];
            top = 0;
        }

        public void push(int value) {
            if (top == data.length) {
                expandCapacity();          // automatic growth
            }
            data[top++] = value;
        }

        public int pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            return data[--top];
        }

        public int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            return data[top - 1];
        }

        public boolean isEmpty() {
            return top == 0;
        }

        public int size() {
            return top;
        }

        private void expandCapacity() {
            int[] bigger = new int[data.length * 2];   // double
            for (int i = 0; i < data.length; i++) {
                bigger[i] = data[i];
            }
            data = bigger;
        }
    }

    // ============================================================
    // MAIN PROBLEM: reverse the whole array using ArrayStack
    // ============================================================
    public static int[] reverseArray(int[] numbers) {
        // Create an ArrayStack with initial capacity 2
        ArrayStack stack = new ArrayStack(2);

        // Push all numbers onto the stack
        for (int i = 0; i < numbers.length; i++) {
            stack.push(numbers[i]);
        }

        // Create an array for reversed numbers
        int[] reversed = new int[numbers.length];

        // Pop elements into the new array
        int i = 0;
        while (!stack.isEmpty()) {
            reversed[i] = stack.pop();
            i++;
        }

        // Return the reversed array
        return reversed;
    }

    // ============================================================
    // BONUS CHALLENGE: reverse only the first K elements
    // ============================================================
    public static int[] reverseFirstK(int[] numbers, int k) {
        // Guard against invalid k
        if (k > numbers.length) k = numbers.length;
        if (k <= 1) return numbers.clone();

        // Create an ArrayStack with initial capacity 2
        ArrayStack stack = new ArrayStack(2);

        // Push only the first K elements
        for (int i = 0; i < k; i++) {
            stack.push(numbers[i]);
        }

        // Pop them back into the first K positions (reverses that prefix)
        int i = 0;
        while (!stack.isEmpty()) {
            numbers[i] = stack.pop();
            i++;
        }

        // Return the array
        return numbers;
    }

    // ============================================================
    // Main method — tests both the main problem and the bonus
    // ============================================================
    public static void main(String[] args) {

        // ---- Main problem ----
        System.out.println("=== Main Problem: reverseArray ===");

        int[] numbers = {10, 20, 30, 40, 50};
        int[] result = reverseArray(numbers);
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();   // 50 40 30 20 10

        // Document examples
        System.out.println("Example 1: " +
                java.util.Arrays.toString(reverseArray(new int[]{101, 102, 103, 104, 105})));
        // [105, 104, 103, 102, 101]

        System.out.println("Example 2: " +
                java.util.Arrays.toString(reverseArray(new int[]{10, 20, 30})));
        // [30, 20, 10]

        System.out.println("Example 3: " +
                java.util.Arrays.toString(reverseArray(new int[]{7})));
        // [7]

        // ---- Bonus challenge ----
        System.out.println("\n=== Bonus Challenge: reverseFirstK ===");

        int[] bonus = {10, 20, 30, 40, 50};
        int[] bonusResult = reverseFirstK(bonus, 3);
        for (int num : bonusResult) {
            System.out.print(num + " ");
        }
        System.out.println();   // 30 20 10 40 50
    }
}

