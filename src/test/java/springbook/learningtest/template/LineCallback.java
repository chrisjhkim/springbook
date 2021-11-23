package springbook.learningtest.template;

public interface LineCallback<T> {
	// 3-38
	// 3-41
	T doSomethingWithLine(String line, T value);
}
