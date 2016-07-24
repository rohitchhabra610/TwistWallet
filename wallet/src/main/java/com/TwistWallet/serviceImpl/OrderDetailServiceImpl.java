package com.TwistWallet.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TwistWallet.Entity.CartEntity;
import com.TwistWallet.Entity.LoginEntity;
import com.TwistWallet.Entity.OrderDetailsEntity;
import com.TwistWallet.Entity.ProductEntity;
import com.TwistWallet.Entity.UserEntity;
import com.TwistWallet.dao.BaseDao;
import com.TwistWallet.dto.Cart;
import com.TwistWallet.dto.Login;
import com.TwistWallet.dto.MailUser;
import com.TwistWallet.dto.Product;
import com.TwistWallet.service.OrderDetailService;
import com.TwistWallet.service.UserService;
import com.TwistWallet.utils.Response;
import com.TwistWallet.utils.TwistWalletRequest;
import com.TwistWallet.utils.TwistWalletResponse;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	BaseDao baseDaoImpl;
	
	@Autowired
	UserService userService;
	
	@Override
	public TwistWalletResponse placeOrder(TwistWalletRequest request) throws Exception {
		TwistWalletResponse response = new TwistWalletResponse();
		if(request.getOrderDetails().getUser() != null && 
				request.getOrderDetails().getUser().getEmailAddress() != null &&
			request.getOrderDetails().getSeller() != null )
		{
			UserEntity user = new UserEntity();
			user.setUserId(request.getOrderDetails().getUser().getUserId());
			user.setFirstName(request.getOrderDetails().getUser().getFirstName());
			user.setLastName(request.getOrderDetails().getUser().getLastName());
			user.setEmailAddress(request.getOrderDetails().getUser().getEmailAddress());
			user.setMobileNumber(request.getOrderDetails().getUser().getMobileNumber());
			user.setAddress(request.getOrderDetails().getUser().getAddress());
			user.setPostelCode(request.getOrderDetails().getUser().getPostelCode());
			user.setAdmin(false);
			baseDaoImpl.update(user);
			
			//SellerEntity sellerEntity = new SellerEntity();
			//sellerEntity.setSellerId(request.getOrderDetails().getSeller().getSellerId());
			
			OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
			//orderDetailsEntity.setSeller(sellerEntity);
			orderDetailsEntity.setUser(user);
			orderDetailsEntity.setAmount(request.getOrderDetails().getAmount());
			orderDetailsEntity.setOrderStatus("Pending");
			orderDetailsEntity.setOrderDetails(request.getOrderDetails().getOrderInfo());
			baseDaoImpl.save(orderDetailsEntity);
			
			//deleting order from cart
			deleteLoginFromCart(request.getLogin().getLoginId());
			//send mail for order confirmation
			MailUser mailUser = new MailUser();
			mailUser.setEmailAddress(user.getEmailAddress());
			mailUser.setSubject("TwistWallet Order-Confirmation");
			mailUser.setTemplateName("TwistwalletOrderConfirmation.html");
			mailUser.setUserName(user.getFirstName());
			request.setMailUser(mailUser);
			userService.sendMail(request);
			
			response.setResultCode(Response.SUCCESS.getResultCode());
			response.setResultDesc(Response.SUCCESS.getDesc());
			return response;
		}
		response.setResultCode(Response.FAILURE.getResultCode());
		response.setResultDesc(Response.FAILURE.getDesc());
		return response;
	}

	@Override
	public TwistWalletResponse addToCart(TwistWalletRequest request) {
		TwistWalletResponse response = new TwistWalletResponse();
		CartEntity cartEntity = new CartEntity();
		
		LoginEntity login=new LoginEntity();
		login.setLoginId(request.getCart().getLogin().getLoginId());
		ProductEntity product = new ProductEntity();
		product.setProductId(request.getCart().getProduct().getProductId());
		cartEntity.setLogin(login);
		cartEntity.setProduct(product);
	
		
/*		Map<String,Object> map=new HashedMap(1);
		map.put("loginId",request.getCart().getLogin().getLoginId());
		LoginEntity loginEntity = (LoginEntity)baseDaoImpl.findWithNamedQueries("login.findByLoginId", LoginEntity.class, map);
		map.clear();
		map.put("productId",request.getCart().getProduct().getProductId());
		ProductEntity productEntity = (ProductEntity)baseDaoImpl.findWithNamedQueries("product.findByProductId", ProductEntity.class, map);
		//cartEntity.setLogin(loginEntity);
		//cartEntity.setProduct(productEntity);*/
		
		cartEntity.setQuantity(request.getCart().getQuantity());
		baseDaoImpl.save(cartEntity);
		response.setResultCode(Response.SUCCESS.getResultCode());
		response.setResultDesc(Response.SUCCESS.getDesc());
		return response;
	}

	@Override
	public TwistWalletResponse getCart(TwistWalletRequest request) {
		TwistWalletResponse response = new TwistWalletResponse();
		float totalAmount=0;
		Map<String,Object> map=new HashedMap(1);
		map.put("loginId", request.getLogin().getLoginId());
		List<CartEntity> cartEntityList=(List<CartEntity>)baseDaoImpl.findWithNamedQuery("cart.findByLoginId", CartEntity.class, map);
		if(cartEntityList!=null){
			List<Cart> cartList = new ArrayList<Cart>();
			for(CartEntity cartEntity:cartEntityList){
			Cart cart=new Cart();
			Login login = new Login();
			login.setLoginId(cartEntity.getLogin().getLoginId());
			cart.setLogin(login);
			Product product=new Product();
			float pricePerProduct=cartEntity.getProduct().getPrice();
			totalAmount+=pricePerProduct;
			product.setPrice(cartEntity.getProduct().getPrice());
			product.setProductName(cartEntity.getProduct().getProductDetail());
			product.setProductCode(cartEntity.getProduct().getProductCode());
			product.setProductDetail(cartEntity.getProduct().getProductDetail());
			cart.setProduct(product);
			cart.setQuantity(cartEntity.getQuantity());
			cart.setCartId(cartEntity.getCartId());
			cartList.add(cart);
			}
			response.setCartList(cartList);
			response.setTotalAmount(totalAmount);
		}
		response.setResultCode(Response.SUCCESS.getResultCode());
		response.setResultDesc(Response.SUCCESS.getDesc());
		return response;
	}

	@Override
	public TwistWalletResponse deleteFromCart(int cartId) throws Exception {
		TwistWalletResponse response = new TwistWalletResponse();
		Map<String,Object> params=new HashedMap();
		params.put("cartId", cartId);
		CartEntity cart=(CartEntity)baseDaoImpl.findWithNamedQueries("cart.findByCartId", CartEntity.class, params);
		if(cart!=null)
			System.out.println("cart NOt null");
		baseDaoImpl.remove(cart);
		response.setResultCode(Response.SUCCESS.getResultCode());
		response.setResultDesc(Response.SUCCESS.getDesc());
		return response;
	}

	@Override
	public TwistWalletResponse deleteLoginFromCart(int loginId) {
		TwistWalletResponse response = new TwistWalletResponse();
		Map<String,Object> map=new HashedMap(1);
		map.put("loginId", loginId);
		List<CartEntity> cartEntityList=(List<CartEntity>)baseDaoImpl.findWithNamedQuery("cart.findByLoginId", CartEntity.class, map);
		if(cartEntityList!=null){
			for(CartEntity cartEntity:cartEntityList){
				baseDaoImpl.remove(cartEntity);
			}
		response.setResultCode(Response.SUCCESS.getResultCode());
		response.setResultDesc(Response.SUCCESS.getDesc());
		return response;
		}else{
			response.setResultCode(Response.FAILURE.getResultCode());
			response.setResultDesc(Response.FAILURE.getDesc());
			return response;
		}
	}

}
