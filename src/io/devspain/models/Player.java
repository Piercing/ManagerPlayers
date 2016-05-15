package io.devspain.models;

public class Player {

	private String mName;
	private int mAge;
	private boolean mState;
	private long mCode;

	public Player() {
		// disabled defalut contructor, customer requirement
	}

	public Player(String name, int age, boolean state, long codigo) {
		super();
		this.mName = name;
		this.mAge = age;
		this.mState = state;
		this.mCode = codigo;
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public int getAge() {
		return mAge;
	}

	public void setAge(int age) {
		if (age >= 16 || age <= 50) {
			this.mAge = age;
		} else {
			return;
		}
	}

	public boolean getState() {
		return mState;
	}

	public void setState(boolean state) {
		this.mState = state;
	}

	public long getCode() {
		return mCode;
	}

	public void setCode(long code) {
		this.mCode = code;
	}

}
