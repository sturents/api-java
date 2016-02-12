package com.sturents.api;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

public class MessageHasherJUnitTest {

    public MessageHasher testSubject = new MessageHasher();

    @Test
    public void shouldCalculateHash() throws NoSuchAlgorithmException {
	// Given
	String testString = "test";
	String expected = "098f6bcd4621d373cade4e832627b4f6";

	// When
	String result = testSubject.hashThis(testString);
	System.out.println(result);

	// Then
	assertThat(result, is(expected));
    }

}
