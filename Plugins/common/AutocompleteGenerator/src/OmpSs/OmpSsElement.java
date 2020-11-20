package OmpSs;

import java.util.List;

import OmpSs.OmpSs.OmpSsType;

import java.util.ArrayList;


public class OmpSsElement {

	private List<OmpSsElement> children = null;
	private OmpSsType value;
	private boolean repeatable = false;
	public OmpSsElement(OmpSsType p, boolean rep)
	{
		children  = new ArrayList<OmpSsElement>();
		value = p;
		repeatable = rep;
	}
	
	public OmpSsElement(OmpSsType ppragma)
	{
		children  = new ArrayList<OmpSsElement>();
		value = ppragma;
	}
	
	public OmpSsElement add(OmpSsElement ...pe)
	{
		for(OmpSsElement el : pe)
		children.add(el);
		return this;
	}
	
	public OmpSsElement add( OmpSsElement[]  ...ar)
	{
		for(OmpSsElement[] T : ar)
			for (int i = 0; i < T.length; ++i) {
				add(T[i]);	
			}
		return this;
		
	}
	
	public OmpSsElement add(OmpSsType pe, boolean repeatable)
	{
		children.add(new OmpSsElement(pe, repeatable));
		return this;
	}
	
	public OmpSsElement add(OmpSsType ...pe)
	{
		for(OmpSsType el : pe)
		children.add(new OmpSsElement(el));
		return this;
	}
	
	public OmpSsElement addLastChild(OmpSsType ...pe)
	{
		for(OmpSsType el : pe)
		children.get(children.size()-1).add(new OmpSsElement(el));
		return this;
	}
	
	public OmpSsElement add( OmpSsType[]  ...ar)
	{
		for(OmpSsType[] T : ar)
			for (int i = 0; i < T.length; ++i) {
				add(T[i]);	
			}
		return this;
		
	}
	
	
	public OmpSsType getValue()
	{
		return value;
	}
	
	public List<OmpSsElement> getNextStates()
	{
		return children;
	}
	
	public OmpSsElement obtainOfType(OmpSsType p)
	{
		for(OmpSsElement c:children)
		{
			if(c.getValue().equals(p)) return c;
		}
		return null;
	}
	public boolean isRepeatable()
	{
		return repeatable;
	}
	
}
