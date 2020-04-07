import java.io.*;

//Building class to store each building record from the dataset

public class Building implements Serializable {
	private static final long serialVersionUID = 6529685098267757690L;
	
	//Columns from the dataset
	int censusYear, blockID, propID, baseID, constructYear, refurbishYear, numOfFloors, accessRating,hasBicycle;
	boolean hasShower;
	Double xCoord, yCoord;
	String buildingName, streetAddr, smallArea, predominantSpace, accessType, accessDesc, location;
	
	//Constructor to initialize the non null values
	public Building(int censusYear, int blockID, int propID, int baseID, int numOfFloors, String smallArea,
			String predominantSpace) {
		super();
		this.censusYear = censusYear;
		this.blockID = blockID;
		this.propID = propID;
		this.baseID = baseID;
		this.numOfFloors = numOfFloors;
		this.smallArea = smallArea;
		this.predominantSpace = predominantSpace;
	}
	
	//Getters and setters
	public int getCensusYear() {
		return censusYear;
	}
	public void setCensusYear(int censusYear) {
		this.censusYear = censusYear;
	}
	public int getBlockID() {
		return blockID;
	}
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	public int getPropID() {
		return propID;
	}
	public void setPropID(int propID) {
		this.propID = propID;
	}
	public int getBaseID() {
		return baseID;
	}
	public void setBaseID(int baseID) {
		this.baseID = baseID;
	}
	public int getConstructYear() {
		return constructYear;
	}
	public void setConstructYear(int constructYear) {
		this.constructYear = constructYear;
	}
	public int getRefurbishYear() {
		return refurbishYear;
	}
	public void setRefurbishYear(int refurbishYear) {
		this.refurbishYear = refurbishYear;
	}
	public int getNumOfFloors() {
		return numOfFloors;
	}
	public void setNumOfFloors(int numOfFloors) {
		this.numOfFloors = numOfFloors;
	}
	public int getAccessRating() {
		return accessRating;
	}
	public void setAccessRating(int accessRating) {
		this.accessRating = accessRating;
	}
	public boolean isHasShower() {
		return hasShower;
	}
	public void setHasShower(boolean hasShower) {
		this.hasShower = hasShower;
	}
	public int isHasBicycle() {
		return hasBicycle;
	}
	public void setHasBicycle(int hasBicycle) {
		this.hasBicycle = hasBicycle;
	}
	public Double getxCoord() {
		return xCoord;
	}
	public void setxCoord(Double xCoord) {
		this.xCoord = xCoord;
	}
	public Double getyCoord() {
		return yCoord;
	}
	public void setyCoord(Double yCoord) {
		this.yCoord = yCoord;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getStreetAddr() {
		return streetAddr;
	}
	public void setStreetAddr(String streetAddr) {
		this.streetAddr = streetAddr;
	}
	public String getSmallArea() {
		return smallArea;
	}
	public void setSmallArea(String smallArea) {
		this.smallArea = smallArea;
	}
	public String getPredominantSpace() {
		return predominantSpace;
	}
	public void setPredominantSpace(String predominantSpace) {
		this.predominantSpace = predominantSpace;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getAccessDesc() {
		return accessDesc;
	}
	public void setAccessDesc(String accessDesc) {
		this.accessDesc = accessDesc;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public int byteAllocation() {
		int pageSize = 0;

		//for int
		pageSize += 36;
		
		//for float
		pageSize += 8;
		
		//for bool
		pageSize += 1;

		//for strings
		if(buildingName != null){
			pageSize += (50 *2);
		}

		if(streetAddr != null){
			pageSize += (100 *2);
		}

		if(smallArea != null){
			pageSize += (25 * 2);
		}

		if(predominantSpace != null){
			pageSize += (50 * 2);
		}

		if(accessType != null){
			pageSize += (50 * 2);
		}
		
		if(accessDesc != null){
			pageSize += (200 * 2);
		}
		
		if(location != null){
			pageSize += (100 * 2);
		}
		
		return pageSize;
		
	}
	
	
}
