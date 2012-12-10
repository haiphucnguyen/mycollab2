package com.esofthead.mycollab.core.arguments;

public class BooleanSearchField extends SearchField {
    
        public static String IS = "is";
        
        public static String NOT = "is not";
    
	private boolean value;
	
        private String comparision;
	
	
	public BooleanSearchField(String oper, boolean value) {
		this(oper, BooleanSearchField.IS, value);
	}
        
        public BooleanSearchField(String oper, String comparision, boolean value) {
            this.operation = oper;
            this.comparision = comparision;
            this.value = value;
        }

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

}
