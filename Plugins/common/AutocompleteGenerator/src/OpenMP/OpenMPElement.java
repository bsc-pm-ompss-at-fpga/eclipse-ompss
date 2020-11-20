package OpenMP;

import java.util.List;

import OpenMP.OpenMP.OpenMPType;

import java.util.ArrayList;


public class OpenMPElement {

	private List<OpenMPElement> children = null;
	private OpenMPType value;
	private boolean repeatable = false;
	public OpenMPElement(OpenMPType p, boolean rep)
	{
		children  = new ArrayList<OpenMPElement>();
		value = p;
		repeatable = rep;
	}
	
	public OpenMPElement(OpenMPType ppragma)
	{
		children  = new ArrayList<OpenMPElement>();
		value = ppragma;
	}
	
	public void add(OpenMPElement pe)
	{
		children.add(pe);
	}
	
	public OpenMPType getValue()
	{
		return value;
	}
	
	public List<OpenMPElement> getNextStates()
	{
		return children;
	}
	
	public OpenMPElement obtainOfType(OpenMPType p)
	{
		for(OpenMPElement c:children)
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
