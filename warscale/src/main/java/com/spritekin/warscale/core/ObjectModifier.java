package com.spritekin.warscale.core;

//Links an object with another object by using some relation link
//This object function is to add some additional information to the link
// like a uid, a timestamp, a relation name
public class ObjectModifier {
	private WarscaleObject modifier = null;
	private long uid = 0;
	private long timestamp = System.nanoTime();
	private String relation = "";
	
	public ObjectModifier(WarscaleObject modifier, String relation) {
		this.modifier = modifier;
		this.relation = relation;
		uid = com.spritekin.warscale.utils.UIDGenerator.getUID();
	}
	
	//The equals method only cares if it is the same object.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return false;
	}	

	public WarscaleObject getModifier() {
		return modifier;
	}

	public void setModifier(WarscaleObject modifier) {
		this.modifier = modifier;
	}

	public long getUid() {
		return uid;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public String getRelation() {
		return relation;
	}

	public void resetTimeStamp() {
		timestamp = 0l;
	}

	
	
}
