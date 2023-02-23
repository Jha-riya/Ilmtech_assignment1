package com.example.Tutorials.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Tutorials.model.Tutorial;
import com.example.Tutorials.repository.Tutorial_Repository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class Tutorial_Controller {
	@Autowired 
	Tutorial_Repository tutorial_Repository;
	
	@GetMapping("")
	public ResponseEntity<?> defaultGet()
	{
			return new ResponseEntity<String>("Default Path of API Status is: "+HttpStatus.OK,HttpStatus.OK);
	}
	
	@GetMapping("/tutorials")
	public ResponseEntity<?> getAllTutorial(@RequestParam(required= false) String title){
		try {
			List<Tutorial> demo = new ArrayList<Tutorial>();
			if (title == null)
				tutorial_Repository.findAll().forEach(demo::add);
			else
				tutorial_Repository.findByTitleContaining(title).forEach(demo::add);
			if(demo.isEmpty()) {
				return new ResponseEntity<String>("No data found",HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(demo, HttpStatus.OK);
				
		}catch (Exception e) 
		{
		
			return new ResponseEntity<>("Excpetion occurred: Message: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
@GetMapping("/tutorials/{id}")
public ResponseEntity<?> getTutorialById(@PathVariable ("id") long id){
	Optional<Tutorial> demoData = tutorial_Repository.findById(id);
	if(demoData.isPresent()) {
		return new ResponseEntity<>(demoData.get(), HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>("No data found",HttpStatus.NOT_FOUND);
	}
	}
@GetMapping("/tutorials/{title}")
public ResponseEntity<?> getTutorialByTitle(@PathVariable ("title") String title){
	Optional<Tutorial> demoData = tutorial_Repository.findByTitle(title);
	if(demoData.isPresent()) {
		return new ResponseEntity<>(demoData.get(), HttpStatus.OK);
	}
	else {
		return new ResponseEntity<String>(title+" No data found"+demoData.get(),HttpStatus.NOT_FOUND);
	}
	}
@PostMapping("/tutorials")
public ResponseEntity<?> createTutorial(@RequestBody Tutorial demo ){
try {
	Tutorial _demo = tutorial_Repository.save(new Tutorial(demo.getTitle(), demo.getDescription(),false));
	return new ResponseEntity<>(_demo, HttpStatus.CREATED);
}catch (Exception e) {
	return new ResponseEntity<>("Excpetion occurred: Message: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
}
}
@PutMapping("/tutorials/{id}")
public ResponseEntity<?> updateTutorial(@PathVariable("id")long id, @RequestBody Tutorial demo){
	Optional<Tutorial> demoData = tutorial_Repository.findById(id);
	if(demoData.isPresent()) {
		Tutorial _demo =  demoData.get();
		_demo.setTitle(demo.getTitle());
		_demo.setDescription(demo.getDescription());
		_demo.setpublished(demo.ispublished());
		return new ResponseEntity<>(tutorial_Repository.save(_demo), HttpStatus.OK);
	}else {
		return new ResponseEntity<String>("No data found",HttpStatus.NOT_FOUND);
	}
}
@DeleteMapping("/tutorials/{id}")
public ResponseEntity<?> deleteTutorial(@PathVariable("id")long id){
	try {
		tutorial_Repository.deleteById(id);
		return new ResponseEntity<String>("Record Deleted Successfully",HttpStatus.OK);
	}catch(Exception e) {
		return new ResponseEntity<>("Excpetion occurred: Message: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@DeleteMapping("/tutorials")
public ResponseEntity<?> deleteAllTutorial(){
	try {
		tutorial_Repository.deleteAll();
		return new ResponseEntity<String>("Records Deleted Successfully",HttpStatus.OK);
	}catch(Exception e) {
		return new ResponseEntity<>("Excpetion occurred: Message: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@GetMapping("/tutorials/published")
public ResponseEntity<?> findByPublished(){
	try {
		List<Tutorial> demo = tutorial_Repository.findByPublished(true);
		if(demo.isEmpty()) {
			return new ResponseEntity<String>("No data found",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(demo, HttpStatus.OK);
	}catch(Exception e) {
		return new ResponseEntity<>("Excpetion occurred: Message: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@GetMapping("/tutorials/notpublished")
public ResponseEntity<?> findByNotPublished(){
	try {
		List<Tutorial> demo = tutorial_Repository.findByPublished(false);
		if(demo.isEmpty()) {
			return new ResponseEntity<String>("No data found",HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(demo, HttpStatus.OK);
	}catch(Exception e) {
		return new ResponseEntity<>("Excpetion occurred: Message: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
}


