package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.restaurant.entities.Customer;
import com.example.restaurant.service.CustomerService;
import com.example.restaurant.service.ProductService;

@Controller
@RequestMapping("/dashboard/admin")  // các đường dẫn liên quan đến admin sẽ sử dụng /dashboard/admin làm tiền tố
public class AdminController {

    private final CustomerService customerService;
    private final ProductService productService;

    public AdminController(CustomerService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
    }

    // Trang quản lý khách hàng
    @GetMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "layouts/admin/customers";
    }

    // Trang quản lý sản phẩm
    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "layouts/admin/products";
    }

    // Trang Dashboard admin
    @GetMapping("/")
    public String adminDashboard() {
        return "layouts/admin/admin-dashboard";
    }

    // Thêm khách hàng
    @GetMapping("/customers/new")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer()); // Tạo đối tượng Customer rỗng cho form
        return "layouts/admin/add-customer"; // Chỉ định view cho form thêm khách hàng
    }

    @PostMapping("/customers")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.addCustomer(customer); // Gọi service để thêm khách hàng
        return "redirect:/dashboard/admin/customers"; // Sau khi thêm, chuyển hướng về trang danh sách khách hàng
    }

    // Sửa khách hàng
    @GetMapping("/customers/edit/{id}")
    public String showEditCustomerForm(@PathVariable Integer id, Model model) {
        Customer customer = customerService.getCustomerById(id); // Lấy khách hàng theo ID
        if (customer == null) {
            return "redirect:/dashboard/admin/customers"; // Nếu không tìm thấy, chuyển hướng về danh sách khách hàng
        }
        model.addAttribute("customer", customer); // Gửi đối tượng customer vào form sửa
        return "layouts/admin/edit-customer"; // Chỉ định view cho form sửa khách hàng
    }

    @PostMapping("/customers/{id}")
    public String updateCustomer(@PathVariable Integer id, @ModelAttribute Customer customer) {
        customer.setId(id); // Đảm bảo rằng ID của khách hàng không bị thay đổi
        customerService.updateCustomer(customer); // Cập nhật khách hàng
        return "redirect:/dashboard/admin/customers"; // Sau khi cập nhật, chuyển hướng về trang danh sách khách hàng
    }

    // Xóa khách hàng
    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        boolean isDeleted = customerService.deleteCustomer(id); // Xóa khách hàng theo ID
        if (!isDeleted) {
            return "redirect:/dashboard/admin/customers"; // Nếu không xóa được, quay lại trang danh sách khách hàng
        }
        return "redirect:/dashboard/admin/customers"; // Sau khi xóa, chuyển hướng về trang danh sách khách hàng
    }
}
