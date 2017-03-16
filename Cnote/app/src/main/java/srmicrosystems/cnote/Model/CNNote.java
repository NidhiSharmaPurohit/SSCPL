package srmicrosystems.cnote.Model;

/**
 * Created by saman_000 on 02-05-2016.
 */
public class CNNote {
    public String getCNNumber() {
        return CNNumber;
    }

    public void setCNNumber(String CNNumber) {
        this.CNNumber = CNNumber;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public int getPackageNo() {
        return PackageNo;
    }

    public void setPackageNo(int packageNo) {
        PackageNo = packageNo;
    }

    public int getModeID() {
        return ModeID;
    }

    public void setModeID(int modeID) {
        ModeID = modeID;
    }

    public int getFlightId(){
        return FlightId;
    }

    public void setFlightId(int FlightID){
        FlightId = FlightID;
    }


    public double getActualWight() {
        return ActualWeight;
    }

    public void setActualWight(double actualWight) {
        ActualWeight = actualWight;
    }

    public double getConsignmentWeight() {
        return ConsignmentWeight;
    }

    public void setConsignmentWeight(double consignmentWeight) {
        ConsignmentWeight = consignmentWeight;
    }

    public String getMaterialDesc() {
        return MaterialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        MaterialDesc = materialDesc;
    }

   /* public String getConsignerDetails() {
        return ConsignerDetails;
    }

    public void setConsignerDetails(String consignerDetails) {
        ConsignerDetails = consignerDetails;
    }

    public String getConsigneeDetails() {
        return ConsigneeDetails;
    }

    public void setConsigneeDetails(String consigneeDetails) {
        ConsigneeDetails = consigneeDetails;
    }
*/
    public int getOriginCityID() {
        return OriginCityID;
    }

    public void setOriginCityID(int originCityID) {
        OriginCityID = originCityID;
    }

    public int getDestCityID() {
        return DestCityID;
    }

    public void setDestCityID(int destCityID) {
        DestCityID = destCityID;
    }

    public int getToPayMode() {
        return ToPayMode;
    }

    public void setToPayMode(int toPayMode) {
        ToPayMode = toPayMode;
    }

    public double getServiceTax() {
        return ServiceTax;
    }

    public void setServiceTax(double serviceTax) {
        ServiceTax = serviceTax;
    }

    public double getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(double TOTAL) {
        this.TOTAL = TOTAL;
    }

    public  String CNNumber;
    public String BookingDate;
    public  int PackageNo;
    public  int ModeID;
    public double ActualWeight;
    public  double ConsignmentWeight;
    public  String MaterialDesc;
    public  int ShipperCompId;
    public  int ConsigneeCompId;
    public  int OriginCityID;
    public  int DestCityID;
    public int ToPayMode;
    public  double ServiceTax;
    public  double TOTAL;
    public  int CenterID;
    public String HandedBy;
    public String  RecievedBy;
    public int FlightId;
    public  String Remarks;
    public String Status;

    public String getHandedBy() {
        return HandedBy;
    }

    public  void  setHandedBy(String handedBy) {
        HandedBy= handedBy;
    }

    public String getRecievedBy() {
        return RecievedBy;
    }

    public  void  setRecievedBy(String recievedBy) {
        RecievedBy= recievedBy;
    }


    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public int getCenterID() {
        return CenterID;
    }

    public void setCenterID(int centerID) {
        CenterID = centerID;
    }

    public int getConsigneeCompId() {
        return ConsigneeCompId;
    }

    public void setConsigneeCompId(int consigneeCompId) {
        ConsigneeCompId = consigneeCompId;
    }

    public int getShipperCompId() {
        return ShipperCompId;
    }

    public void setShipperCompId(int shipperCompId) {
        ShipperCompId = shipperCompId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


}
