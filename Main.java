import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car
{
	private String carId;
	private String brand;
	private String model;
	private double basepriceperday;
	private boolean isavailable;
	
	public Car(String carId, String brand, String model, double basepriceperday)
	{
		this.carId=carId;
		this.brand=brand;
		this.model=model;
		this.basepriceperday=basepriceperday;
		this.isavailable=true;
		//since the information of the new car is added by the owner or administrator, that new car is available for rent.
	}
	public String getcarId()
	{
		return carId;
	}
	public String getbrand()
	{
		return brand;
	}
	public String getmodel()
	{
		return model;
	}
	public double calculateprice(int rentaldays)
	{
		return basepriceperday*rentaldays;
	}
	public boolean isavailable()
	{
		return isavailable;
	}
	public void rent()
	{
		isavailable=false;
	}
	public void returncar()
	{
		isavailable=true;
	}
	
}
class Customer
{
	private String customername;
	private String customerid;
	Customer(String customername, String customerid)
	{
		this.customername=customername;
		this.customerid=customerid;
	}
	public String getcustomername()
	{
		return customername;
	}
	public String getcustomerid()
	{
		return customerid;
	}
	
}
class Rental
{
	private Car car;
	private Customer customer;
	private int days;
	Rental(Car car, Customer customer, int days)
	{
		this.car=car;
		this.customer=customer;
		this.days=days;
	}
	
	public Car getcar()
	{
		return car;
	}
	public Customer getcustomer()
	{
		return customer;
	}
	public int getdays()
	{
		return days;
	}
	
}
class CarRentalSystem
{
	private List<Car> cars;
	private List<Customer> customers;
	private List<Rental> rentals;
	
	public CarRentalSystem()
	{
		cars=new ArrayList<>();
		customers=new ArrayList<>();
		rentals=new ArrayList<>();
	}
	public void addCar(Car car)
	{
		cars.add(car);
	}
	public void addCustomer(Customer customer)
	{
		customers.add(customer);
	}
	
	public void addRental(Car car, Customer customer, int days)
	{
		if(car.isavailable())
		{
			car.rent();
			rentals.add(new Rental(car, customer, days));
		}
		else
		{
			System.out.println("Car is not avaliable for rent");
		}
	}
	
	public void returncar(Car car)
	{
		car.returncar();//make the isavailable option of the car to true
		Rental rentalToRemove = null;
		for(Rental rental:rentals)
		{
			if(rental.getcar()==car)
			{
				rentalToRemove=rental;
				break;
			}
			
		}
		if(rentalToRemove!=null)
		{
			rentals.remove(rentalToRemove);
			System.out.println("Car returned Successfully");
		}
		else {
			System.out.println("Car was not rented");
		}
		
	}
	public void  menu()
	{
		Scanner scanner = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("==== Car Rental System ====");
			System.out.println("1. Rent a car");
			System.out.println("2. Return a car");
			System.out.println("3. Exit");
			System.out.println("Enter your choice : ");
			
			int choice=scanner.nextInt();
			scanner.nextLine();//consume next line
			
			if(choice==1)
			{
				System.out.println("\n== Rent a car ==\n");
				System.out.println("Enter your name: ");
				String customerName=scanner.nextLine();
				
				System.out.println("\nAvailable cars: ");
				for(Car car : cars)
				{
					if(car.isavailable())
					{
						System.out.println(car.getcarId() + " - " + car.getbrand() + " " + car.getmodel());	
						
					}
				}
				System.out.println("\nEnter the car Id you want to rent:");
				String carId = scanner.next();
				
				System.out.println("\nEnter the number of days for rental: ");
				int rentalDays = scanner.nextInt();
				scanner.nextLine();
				
				
				Customer newCustomer = new Customer(customerName,"CUS" + (customers.size()+1));
				addCustomer(newCustomer);
				
				Car selectedCar = null;
				for(Car car: cars)
				{
					if(car.getcarId().equalsIgnoreCase(carId) && car.isavailable())
					{
						selectedCar = car;
						break;
					}
				}
				if(selectedCar !=null)
				{
					double totalPrice = selectedCar.calculateprice(rentalDays);
					System.out.println("\n == Rental Information ==\n");
					System.out.println("Customer ID: "+ newCustomer.getcustomerid());
					System.out.println("Customer Nmae: "+ newCustomer.getcustomername());
					System.out.println("Car: "+selectedCar.getbrand());
					System.out.println("Rental Days : "+rentalDays);
					System.out.println("Total Price :"+totalPrice);
					
					System.out.println("\nConfirm rental (Y/N): ");
					String confirm = scanner.nextLine();
					
					if(confirm.equalsIgnoreCase("Y"))//equalsIgnoreCase checks the input irrespective of case, the comparision is case insensitive
					{
						addRental(selectedCar, newCustomer, rentalDays);
						System.out.println("\nCar rented successfully.");
					}
					else {
						System.out.println("\nInvalid car selection or car not available for rent.");
					}
				}
			}
				else if(choice==2)
				{
					System.out.println("\n== Return a Car ==\n");
					System.out.println("Enter the car ID you want to return: ");
					String carid = scanner.nextLine();
					
					Car carToReturn=null;
					for(Car car:cars)
					{
						if(car.getcarId().equals(carid) && !car.isavailable())
						{
							carToReturn = car;
							break;
						}
					}
					if (carToReturn !=null)
					{
						Customer customer = null;
						for(Rental rental : rentals)
						{
							if(rental.getcar()==carToReturn)
							{
								customer=rental.getcustomer();
								break;
							}
						}
						if(customer != null)
						{
							returncar(carToReturn);
							System.out.print("Car returned successfully by "+customer.getcustomername());
						}
						else 
						{
							System.out.println("Car was not rented or rental infromation is missing.");
						}
						
					}
					else 
					{
						System.out.println("Invalid car ID or car is not rented.");
					}
				}
				else if (choice==3) 
				{
					break;
				}
				
				else 
				{
					System.out.println("Invalid choice. Please enter a valid option");
				}
	
		}
			System.out.println("\n Thank you for using the car Rental system!");
		
	}
	}

public class Main {

	public static void main(String[] args) {
		CarRentalSystem rentalSystem = new CarRentalSystem();
		 Car car1 = new Car("C001","Toyota","Camry",60.0);
		 Car car2 = new Car("C002","Honda","Accord",70.0);
		 Car car3 = new Car("C003","Mahindra","Thar",150.0);
		 rentalSystem.addCar(car1);
		 rentalSystem.addCar(car2);
		 rentalSystem.addCar(car3);
		 rentalSystem.menu();
	}}
