package jobtest.task3.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import jobtest.task3.db.DbConfig;
import jobtest.task3.db.DbConnection;
import jobtest.task3.exception.ApiError;
import jobtest.task3.exception.IncorrectRequest;
import jobtest.task3.model.entity.Supply;
import jobtest.task3.model.repository.SupplyRepository;

@RestController
class PostingsController {

	private static String ERROR = "The format must be dd.MM.yyyy-dd.MM.yyyy";

	@Autowired
	private DbConfig dbConfig;

	@GetMapping(value = "/supply", produces = MediaType.APPLICATION_JSON_VALUE)
	List<Supply> supply(@RequestParam(name = "from_to", required = false) String fromTo,
			@RequestParam(name = "is_active", required = false) Boolean active)
			throws SQLException, IncorrectRequest {

		Date startDate = null, endDate = null;

		if (fromTo != null) {
			String[] periodArr = fromTo.split("-");
			if (periodArr.length != 2) {
				throw new IncorrectRequest(ERROR);
			}
			try {
				startDate = new SimpleDateFormat("dd.MM.yyyy").parse(periodArr[0]);
				endDate = new SimpleDateFormat("dd.MM.yyyy").parse(periodArr[1]);
			} catch (ParseException ex) {
				throw new IncorrectRequest(ERROR + "; " + ex.getLocalizedMessage());
			}
		}

		var supply = new SupplyRepository(new DbConnection(dbConfig));
		var list = supply.getSupplyByAuthSupplyLimitTime(startDate, endDate, active);
		return list;
	}

	@ExceptionHandler({ IncorrectRequest.class })
	public ResponseEntity<Object> handleIncorrectRequest(Exception ex, WebRequest req) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(),
				"Incorrect requests parameters");
		return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		ApiError apiError = new ApiError(
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Internal error");
		return new ResponseEntity<Object>(apiError, apiError.getStatus());
	}
}