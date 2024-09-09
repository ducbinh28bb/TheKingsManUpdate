package com.shoe.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.shoe.dao.AccountDao;
import com.shoe.dao.FeedbackDao;
import com.shoe.dao.ProductDao;
import com.shoe.entity.Accounts;
import com.shoe.entity.Feedback;
import com.shoe.entity.Product;
import com.shoe.entity.UserAcounts;
import com.shoe.service.FeedbackService;
@Service
public class FeedbackServiceImpl implements FeedbackService{
	@Autowired
	FeedbackDao feedbackDao;
	@Autowired
	ProductDao productDao;
	@Autowired
	AccountDao accountDao;
	@Autowired
	UserAcounts useAcc;
	

	@Override
	public List<Feedback> findByProductID(Integer id) {
		//String feedbackDate;
		// TODO Auto-generated method stub
		Sort sort = Sort.by(Direction.DESC, "feedbackDate");
		return feedbackDao.findByProductID(id,sort);
	}

	@Override
	public List<Feedback> findAll() {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(Direction.DESC, "feedbackDate");
		return feedbackDao.findAll(sort);
	}

	@Override
	public Feedback create(Feedback feedback) {
		// TODO Auto-generated method stub
		//return feedbackDao.save(feedback);
		return feedbackDao.save(feedback);
	}


	@Override
	public Feedback addFeedback(Feedback feedback, Integer id) {
		// TODO Auto-generated method stub
		Accounts account1 = useAcc.User();	
		Long idAc = account1.getAccountId();
		Product product = productDao.findById(id).get();
		Accounts accounts = accountDao.findById(idAc).get();
		
		feedback.setFeedbackDate(new Date());
		feedback.setAccount(accounts);
		feedback.setProduct(product);
		return feedbackDao.save(feedback);
		//return null;
	}

	@Override
	public void delete(Integer id) {
		feedbackDao.deleteById(id);
		
	}

	
}
