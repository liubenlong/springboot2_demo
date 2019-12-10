import java.util.Stack;


public class StackAndQueueConvert {

    private Stack<Integer> stackData; //data栈
    private Stack<Integer> stackMin; //min栈

    /**
     * 初始化栈
     */
    public StackAndQueueConvert() {
        stackData = new Stack<>();
        stackMin = new Stack<>();
    }

    /**
     * 压入操作
     *
     * @param newNum
     */
    public void push(int newNum) {
        // min栈没有元素 直接压入
        if (stackMin.isEmpty()) {
            stackMin.push(newNum);
            // 压入元素小于min栈栈顶元素
        } else if (newNum < getMin()) {
            stackMin.push(newNum);
            // min栈顶元素比压入元素小
        } else {
            // 获取栈顶元素
            int newMin = stackMin.peek();
            // 继续压入
            stackMin.push(newMin);
        }
        // 把元素压入data栈
        stackData.push(newNum);
    }

    /**
     * 移除栈顶元素
     *
     * @return
     */
    public int pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty");
        }
        // 移除min栈栈顶元素
        stackMin.pop();
        // 移除并返回data栈栈顶元素
        return stackData.pop();
    }

    /**
     * 获取最小值
     *
     * @return
     */
    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("Your stack is empty");
        }
        // 返回min栈栈顶元素
        return stackMin.peek();
    }

}