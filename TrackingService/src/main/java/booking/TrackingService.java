package booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Component
@Service
public class TrackingService {
	@Autowired
	Repository repository;
	
	public List<TrackingHistory> getAllHistory(){
		return repository.findAll();
		}
	public TrackingHistory getBookingById(int id) {
		return repository.findById(id).orElse(null);
	}
	public TrackingHistory addHistory(TrackingHistory trackinghistory) {
		repository.save(trackinghistory);
		return trackinghistory;
	}
	public TrackingHistory updateHistory(TrackingHistory trackinghistory) {
		repository.save(trackinghistory);
		return trackinghistory;
	}
	
}
