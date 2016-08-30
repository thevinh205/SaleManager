package sale.table;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	private int id;
	private int customerId;
	private int employeeId;
	private int shipperId;
	private String address;
	private Date createDate;
	private Date requireDate;
	private Date shippedDate;
	private Long totalPrice;
}
