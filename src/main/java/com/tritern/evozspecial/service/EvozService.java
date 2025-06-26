package com.tritern.evozspecial.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tritern.evozspecial.component.PasswordSalt;
import com.tritern.evozspecial.document.EvozDocument;
import com.tritern.evozspecial.entity.EvozEntity;
import com.tritern.evozspecial.entity.FinalEntity;
import com.tritern.evozspecial.repository.EvozRepo;
import com.tritern.evozspecial.repository.EvozRepository;

@Service
public class EvozService {

	private EvozRepo evozrepo;

	private EvozRepository evozrepository;

	private PasswordSalt passwordsalt;

	@Autowired
	public EvozService(EvozRepo evozrepo, EvozRepository evozrepository, PasswordSalt passwordsalt) {
		this.evozrepo = evozrepo;
		this.evozrepository = evozrepository;
		this.passwordsalt = passwordsalt;
	}

	// database Connections
	public FinalEntity mapEntity(String email) {
		try {
			EvozEntity evozentity = evozrepository.findByEmail(email);
			EvozDocument evozDocument = evozrepo.findByEmail(email);
			FinalEntity allData = new FinalEntity();
			if (evozentity != null) {
				allData.setEmail(email);
				allData.setName(evozentity.getName());
				allData.setUsername(evozentity.getUsername());
				if (evozDocument != null) {
					allData.setPhoto(evozDocument.getPhoto());
				}
				return allData;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	// Create User
	public String signUp(EvozEntity evozentity) {
		try {
			if (evozentity != null) {
				if (evozrepository.existsByEmail(evozentity.getEmail())) {
					return "This User is already Exists";
				} else {
					String salt = passwordsalt.getSalt(30);
					String securepass = passwordsalt.generateSecurePassword(evozentity.getPassword(), salt);
					evozentity.setPassword(securepass);
					evozentity.setSalt(salt);
					evozrepository.save(evozentity);

					return "User Added Successfully";
				}
			} else {
				return "No data found";
			}
		} catch (Exception e) {
			throw e;
		}
	}

	// Login User
	public String loginEvozMUser(String email, String password) {
		try {
			EvozEntity evozentity = evozrepository.findByEmail(email);
			if (evozentity != null) {
				String salt = evozentity.getSalt();
				String securepass = passwordsalt.generateSecurePassword(password, salt);
				if (evozentity.getPassword().equals(securepass)) {
					return "Login Successfully";
				} else {
					return "Password is Incorrect";
				}
			} else {
				return "User is invalid";
			}
		} catch (Exception e) {
			throw e;
		}
	}

	// List All User
	public List<EvozEntity> getList(Integer pageNo) {
		try {
			Pageable paging = PageRequest.of(pageNo - 1, 9);
			Page<EvozEntity> pagedResult = evozrepository.findAll(paging);
			return pagedResult.getContent();
		} catch (Exception e) {
			throw e;
		}
	}

	// get Normal List Count
	public int getCount() {
		try {
//			List<EvozEntity> norListLength = evozrepository.findAll();
//			int count = norListLength.size();
			return (int) (evozrepository.count());
		} catch (Exception e) {
			throw e;
		}
	}

	// List Filtered Users
	public List<EvozEntity> getFilter(String keyword, Integer pageNo) {
		try {
			Pageable paging = PageRequest.of(pageNo - 1, 9);
			System.out.println("key");
			System.out.println(keyword);
			if (keyword != null && keyword.length() != 0) {
				List<EvozEntity> searchList = evozrepository.searchUsers(keyword, paging);
				System.out.println(searchList);
				return searchList;
			} else {

				return null;
			}
		} catch (Exception e) {
			throw e;
		}

	}

	// get Search List Count
	public Integer getSearchCount(String keyword) {
		try {
			List<EvozEntity> listLength = evozrepository.searchLength(keyword);
			int searchCount = listLength.size();
			return searchCount;
		} catch (Exception e) {
			throw e;
		}
	}

	// Update User
	public String updateEvozUser(String name, String email, String emailid, String username) {
		try {
			if ( email != null) {
				EvozEntity userUp = evozrepository.findByEmail(email);

				if (userUp.getName().equals(name)) {
					userUp.setEmail(emailid);
					userUp.setUsername(username);
					evozrepository.save(userUp);
					return "Updated the User";
				} else {
					userUp.setName(name);
					userUp.setEmail(emailid);
					userUp.setUsername(username);
					evozrepository.save(userUp);
					return "Updated the User";
				}
			} else {
				return "null error";
			}

		} catch (Exception e) {
			throw e;
		}

	}

	// Delete User
	public String removeUser(String email) {
		try {
			EvozEntity evozentity = evozrepository.findByEmail(email);
			System.out.println(email);
			if (evozentity != null) {
				evozrepository.delete(evozentity);
				return "user deleted";
			} else {
				return "user doesn't exist";
			}

		} catch (Exception e) {
			throw e;
		}
	}

	// Insert Image
	public String insertImage(EvozDocument email) {
		try {

			EvozDocument evozDocument = evozrepo.findByEmail(email.getEmail());
			EvozEntity evozentity = evozrepository.findByEmail(email.getEmail());
			if (evozDocument == null) {
				EvozDocument newDocument = new EvozDocument();
				newDocument.setEmail(evozentity.getEmail());
				newDocument.setPhoto(email.getPhoto());
				evozrepo.save(newDocument);
				return "Photo Added";
			} else if (evozDocument.getEmail().equals(email.getEmail())) {
				List<String> image = evozDocument.getPhoto();
				System.out.println(image);
				if (image == null) {
					List<String> images = new ArrayList<String>();
					images.addAll(email.getPhoto());
					evozDocument.setPhoto(images);
					evozrepo.save(evozDocument);
					return "photo added into the list";
				} else {
					image.addAll(email.getPhoto());
					evozDocument.setPhoto(image);
					evozrepo.save(evozDocument);
					return "photo added into existing the list";
				}
			} else {
				return "Try again later";
			}

		} catch (Exception q) {
			throw q;
		}
	}

	// Get Image
	public EvozDocument getFinalData(String email) {
		try {
			EvozDocument evozDocument = evozrepo.findByEmail(email);
			System.out.println(evozDocument);
			return evozDocument;

		} catch (Exception o) {
			throw o;
		}
	}

}

//public List<FinalEntity> fddata() {
//	try {
//		List<EvozEntity> evozentity = evozrepository.findAll();
//		List<EvozDocument> evozDocument = evozrepo.findAll();
//	
//		List<FinalEntity> allData = new ArrayList<FinalEntity>();
//	
////		if (evozentity != null) {
////			allData.setEmail(evozentity);
////			allData.setName(evozentity.getName());
////			allData.setUsername(evozentity.getUsername());
////			if (evozDocument != null) {
////				allData.setPhoto(evozDocument.getPhoto());
////			}
//		return allData;
////		}
//	} catch (Exception e) {
//		throw e;
//	}
//}

//public FinalEntity showImages(EvozDocument email){
//try {
//	FinalEntity showImg = evozrepo.findByFinalEntity(email.getEmail());
//	return showImg;
//}catch(Exception t){
//	throw t;
//}
//}

//public List<String> showImg(FinalEntity Image){
//	try {
//		
//	}
//}
