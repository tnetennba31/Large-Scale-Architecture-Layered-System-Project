package domainObjects;

import java.util.ArrayList;

import mappers.CompoundMapper;
import quantifiedElementPackage.QuantifiedElement;

/**
 * @author Marlee Lackey
 * @author Taryn Whitman
 */
public class CompoundDomainObject {

	private int compoundID;
	private String compoundName;
	private double compoundMoles;
	private CompoundMapper dataMapper;
	private ArrayList<QuantifiedElement> compoundElements;

	/**
	 * Constructor for CompoundDomainObject
	 * @param dm the dataMapper that made this compound domain object
	 * @throws Exception
	 */
	public CompoundDomainObject(CompoundMapper dm) throws Exception {
		dataMapper = dm;
		setCompoundID(dataMapper.getCompoundID());
		setCompoundName(dataMapper.getCompoundName());
		setCompoundMoles(dataMapper.getMoles());
		setCompoundElements(dataMapper.getMyElements());
		dataMapper.setCdo(this);
	}

	/**
	 * Return the data mapper that created the domain object
	 * 
	 * @return dataMapper
	 */
	public CompoundMapper getDataMapper() {
		return dataMapper;
	}

	/**
	 * Setter for compoundElements, with added business logic
	 * @param elements the list of elements that make up the compound
	 * @throws Exception
	 */
	public void setCompoundElements(ArrayList<QuantifiedElement> elements) throws Exception {
		for (QuantifiedElement e : elements) {
			if (elements.indexOf(e) != elements.lastIndexOf(e)) { // if the first occurance != last occurance, then
																	// there are more than 1 occurances
				throw new Exception("This element is already added!");
			}
		}
		compoundElements = elements;

	}

	/**
	 * Getter for the list of elements in the compound
	 * @return compoundElements
	 */
	public ArrayList<QuantifiedElement> getElements() {
		return compoundElements;
	}

	/**
	 * Setter for compoundMoles
	 * @param moles
	 */
	public void setCompoundMoles(double moles) {
		compoundMoles = moles;

	}

	/**
	 * Getter for compoundMoles
	 * @return compoundMoles
	 */
	public double getCompoundMoles() {
		return compoundMoles;
	}

	/**
	 * Setter for compoundName
	 * @param name
	 */
	public void setCompoundName(String name) {
		compoundName = name;

	}

	/**
	 * Getter for compoundName
	 * @return compoundName
	 */
	public String getCompoundName() {
		return compoundName;
	}

	/**
	 * Setter for compoundID
	 * @param id
	 */
	public void setCompoundID(int id) {
		compoundID = id;

	}

	/**
	 * Getter for compoundID
	 * @return compoundID
	 */
	public int getCompoundID() {
		return compoundID;
	}

	/**
	 * Call the persist() method in DataMapper to persist the changes made to
	 * compound
	 */
	public void persist() {
		dataMapper.persist();
	}

}
