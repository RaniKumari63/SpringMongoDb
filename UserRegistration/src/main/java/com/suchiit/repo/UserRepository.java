package com.suchiit.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.suchiit.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public User findByEmail(String email);
	//@Query("{'email':?1}")
	//Optional <User> findByEmail(String email);
	
	//@Query("{'firstName':?0,'lastName':?1}")
	//public Optional<User> findByFirstAndLastName(String firstname,String lastname);
	
	//@Query(value="{'firstName':?0,'lastName':?1}",fields="{'description':0,'address':0}")
	//public Optional<User> findByFirstAndLastName(String firstname,String lastname);
	
	
	//@Query(value="{'firstName':?0,'lastName':?1}",fields="{'description':1,'address':1}")
		//public Optional<User> findByFirstAndLastName(String firstname,String lastname);
	
	
	//@Query("{'firstName':{$regex:'?0'},'lastName':{$regex:'?1'}}")
		//public Optional<User> findByFirstAndLastName(String firstname,String lastname);
		
	
	//@Query("{'firstName':{$regex:'?0'},'lastName':{$regex:'?1','options':'i'}}")
			//public Optional<User> findByFirstAndLastName(String firstname,String lastname);
			
	
	
	//@Query("{'firstName':{$regex:'?0'},'lastName':{$regex:'?1','options':'i'}}")
	//public Optional<User> findByFirstAndLastName(String firstname,String lastname);
	
	//@Query("{'age':{'$gt':'?0','$lt':'?1'}}")
		//public Optional<User> findByUsersByAgeBetween(int minage,int maxage);
	
	@Query("{'email':?0,'password':?1})")
   	public  Optional<User> findByEmailAndPassword1(String email,String password);

	public User findByEmailAndPassword(String email, String password);

	public default User verifyEmailAndPassword(String email, String password) {
	
		User user = new User();
		Boolean flag = false;
		User values = findByEmail(email);
		if(values!=null)
		{
		if (values.getUserid() != null) {
			flag = true;
		}

		if (flag) {
			user = findByEmailAndPassword(email, password);

		}
		}
		else
		{
			user=null;
		}
		return user;

	}

}
