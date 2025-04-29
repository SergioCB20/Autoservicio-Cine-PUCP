package pe.com.cinepucp.autoservicio.model.venta;

import pe.com.cinepucp.autoservicio.model.comida.Producto;

public class VentaProducto {
    private Integer ventaProductoId;
    private Venta venta;
    private Producto producto;
    private Integer cantidad;
    private Double precioUnitario;

    public VentaProducto(){

    }
    
    public VentaProducto(Venta venta, Producto producto, Integer cantidad, Double precioUnitario) {
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    
    public Integer getVentaProductoId() {
        return ventaProductoId;
    }

    public void setVentaProductoId(Integer ventaProductoId) {
        this.ventaProductoId = ventaProductoId;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
}
