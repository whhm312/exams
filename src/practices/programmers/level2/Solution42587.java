package practices.programmers.level2;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import org.junit.Test;

class WrapPriority {
	int priority = 0;

	WrapPriority(int priority) {
		this.priority = priority;
	}
}

public class Solution42587 {
	public int solution(int[] priorities, int location) {
		System.out.println();
		WrapPriority locationValue = null;
		Queue<WrapPriority> queue = new LinkedList<WrapPriority>();
		for (int i = 0; i < priorities.length; i++) {
			WrapPriority wrapPriority = new WrapPriority(priorities[i]);
			queue.add(wrapPriority);
			System.out.println("wrapPriority : " + wrapPriority);
			if (i == location) {
				locationValue = wrapPriority;
				System.out.println("locationValue : " + locationValue);
			}
		}

		WrapPriority max = max(queue);
		int printOut = 1;
		WrapPriority print = null;
		while (queue.size() > 0) {
			print = queue.poll();
			if (max == locationValue) {
				break;
			} else if (max == print) {
				printOut++;
				max = max(queue);
			} else {
				queue.add(print);
			}
		}

		return printOut;
	}

	private WrapPriority max(Queue<WrapPriority> queue) {
		WrapPriority max = null;
		int intMax = 0;
		List<WrapPriority> list = queue.stream().collect(Collectors.toList());
		for (int i = 0; i < list.size(); i++) {
			if (intMax < list.get(i).priority) {
				max = list.get(i);
				intMax = list.get(i).priority;
			}
		}
		return max;
	}

	@Test
	public void test1() {
		int[] priorities = { 2, 1, 3, 2 };
		int location = 2;
		int expected = 1;
		assertEquals(expected, solution(priorities, location));
	}

	@Test
	public void test2() {
		int[] priorities = { 1, 1, 9, 1, 1, 1 };
		int location = 0;
		int expected = 5;
		assertEquals(expected, solution(priorities, location));
	}
}
