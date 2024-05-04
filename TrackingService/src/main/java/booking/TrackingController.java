package booking;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class TrackingController {
	@Autowired
	TrackingService trackingService;
	
	@GetMapping
	public ResponseEntity <List<TrackingHistory>> getAllHistory(){
		List<TrackingHistory> bookinghistory = trackingService.getAllHistory();
		return new ResponseEntity<>(bookinghistory, HttpStatus.OK);
		
	}
	@GetMapping("/history/id={id}")
	public ResponseEntity<TrackingHistory> getBookingById(@PathVariable("id") int id){
		TrackingHistory trackinghistory = trackingService.getBookingById(id);
		if (trackinghistory != null) {
			return new ResponseEntity<>(trackinghistory, HttpStatus.OK);
		} else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/booking/update/id={id}")
    public ResponseEntity<TrackingHistory> updateHistory(@PathVariable("id") int id, @RequestBody TrackingHistory History) {
        try {
            TrackingHistory existHistory = trackingService.getBookingById(id);
            if (existHistory != null) {
            	existHistory.setId(History.getUserId());
            	existHistory.setUserId(History.getUserId());
            	existHistory.setConcertId(History.getConcertId());
            	existHistory.setDate(History.getDate());
            	existHistory.setStatus(History.getStatus());
            	TrackingHistory updateHistory = trackingService.updateHistory(existHistory);
                return new ResponseEntity<>(existHistory, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }catch (Exception e) {
            	 return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
     }
	@PostMapping("/booking/add")
	public TrackingHistory addHistory(@RequestBody TrackingHistory History) {
		return trackingService.addHistory(History);
	}
}

