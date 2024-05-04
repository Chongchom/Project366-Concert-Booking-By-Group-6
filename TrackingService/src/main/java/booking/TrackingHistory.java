package booking;





import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="Trackinghistory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="TrackingID")
	private int TrackingID;
	@Column(name="UserID")
	private int UserID;
	@Column(name="ConcertID")
	private int ConcertID;
	@Column(name="Date")
	private String Date;
	@Column(name="Status")
	private String Status;

	
	public TrackingHistory() {
		
	}
	public TrackingHistory(TrackingHistory Tracking) {
		this.TrackingID = Tracking.getId();
		this.UserID = Tracking.getUserId();
		this.ConcertID = Tracking.getConcertId();
		this.Date = Tracking.getDate();
		this.Status = Tracking.getStatus();
	}
	public TrackingHistory(int TrackingID, int UserID, int ConcertID, String Date, String Status) {
		this.TrackingID = TrackingID;
		this.UserID = UserID;
		this.ConcertID = ConcertID;
		this.Date = Date;
		this.Status = Status;
	}
	public int getId() {
		return TrackingID;
	}
	public void setId(int TrackingID) {
		this.TrackingID = TrackingID;
	}
	public int getUserId() {
		return UserID;
	}
	public void setUserId(int UserID) {
		this.UserID = UserID;
	}
	public int getConcertId() {
		return ConcertID;
	}
	public void setConcertId(int ConcertID) {
		this.ConcertID = ConcertID;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String Date) {
		this.Date = Date;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String Status) {
		this.Status = Status;
	}
}
