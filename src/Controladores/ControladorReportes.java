package Controladores;
import Clases.Reporte;
import Modelo.ModeloReporteGeneral;
import java.awt.Color;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;


/* @author Yolanda */
public class ControladorReportes {
    ModeloReporteGeneral MdlReportePer;

    public ControladorReportes() {
        MdlReportePer = new ModeloReporteGeneral();
    }
    
    public List<Reporte> CntReportePersona() {
        return MdlReportePer.MdlReportePersona();
    }
    
    public ChartPanel CntGraficaPersona() {// return the type grafic for put in panel
        List<Reporte> Reportes = MdlReportePer.MdlReportePersona();
        DefaultPieDataset DatosGrafica = new DefaultPieDataset();// Graphic type data
        for (Reporte Reporte : Reportes) {
            DatosGrafica.setValue(Reporte.getTipo(), Reporte.getContador());
            System.out.println(Reporte.getTipo());
            System.out.println(Reporte.getContador());
        }// Put Values in format data
        JFreeChart Grafica = ChartFactory.createPieChart3D("Agentes/Clientes", DatosGrafica);// inthis part you have a graphic model
        //If you want a normal graficj
        //ChartPanel GraficaPanel = new ChartPanel(Grafica);//put in panel without information and messy
        PiePlot3D Plot = (PiePlot3D) Grafica.getPlot();//Is how if you put 2 grapivh in the same in other position for simulate this effect
        Plot.setForegroundAlpha(0.3f);//Traslation with its axis
        Plot.setSectionPaint("Cliente", Color.blue);//Comparable is String of something
        Plot.setSectionPaint("AgenteS", Color.orange);   
        ChartPanel GraficaPanel = new ChartPanel(Grafica);
        GraficaPanel.setMouseWheelEnabled(true);
        GraficaPanel.setDomainZoomable(true);
        GraficaPanel.setEnforceFileExtensions(true);
        return GraficaPanel;
    }
    public ChartPanel CntGraficaVuelo() {// return the type grafic for put in panel
        List<Reporte> Reportes = MdlReportePer.MdlReporteVuelo();
        DefaultPieDataset DatosGrafica = new DefaultPieDataset();// Graphic type data
        for (Reporte Reporte : Reportes) {
            DatosGrafica.setValue(Reporte.getTipo(), Reporte.getContador());
            System.out.println(Reporte.getTipo());
            System.out.println(Reporte.getContador());
        }// Put Values in format data
        JFreeChart Grafica = ChartFactory.createPieChart3D("Tipos Vuelos", DatosGrafica);// inthis part you have a graphic model
        //If you want a normal graficj
        //ChartPanel GraficaPanel = new ChartPanel(Grafica);//put in panel without information and messy
        PiePlot3D Plot = (PiePlot3D) Grafica.getPlot();//Is how if you put 2 grapivh in the same in other position for simulate this effect
        Plot.setForegroundAlpha(0.3f);//Traslation with its axis
        Plot.setSectionPaint("Internacional", Color.blue);//Comparable is String of something
        Plot.setSectionPaint("Nacional", Color.orange);   
        ChartPanel GraficaPanel = new ChartPanel(Grafica);
        GraficaPanel.setMouseWheelEnabled(true);
        GraficaPanel.setDomainZoomable(true);
        return GraficaPanel;
    }
    
    public ChartPanel CntGraficarClientesTipo() {// return the type grafic for put in panel
        List<Reporte> Reportes = MdlReportePer.MdlReporteCliente();
        DefaultCategoryDataset DatosGrafica = new DefaultCategoryDataset();// Graphic type data
        for (Reporte Reporte : Reportes) {
            DatosGrafica.setValue(Reporte.getContador(),"Tipo Cliente",Reporte.getTipo());
        }// Put Values in format data .createBarChart3D("Eleccion de Cliente", "Tipo", "Numero Personas", DatosGrafica,PlotOrientation.VERTICAL , true, true, true);
        //JFreeChart Grafica  = ChartFactory.createBarChart3D("Eleccion de Cliente", "Tipo", "Numero Personas", DatosGrafica,PlotOrientation.VERTICAL , true, true, true);
        //ChartPanel GraficaPanel = new ChartPanel(Grafica);
        //GraficaPanel.setMouseWheelEnabled(true);//beuty command
        //GraficaPanel.setDomainZoomable(true);
        //GraficaPanel.setBackground(Color.red);
        //return GraficaPanel;
        JFreeChart Grafica = ChartFactory.createBarChart("Eleccion cliente", "tipo", "Numero perosna", DatosGrafica);
        ChartPanel GraficaPanel = new ChartPanel(Grafica);
        return GraficaPanel;
    }
   
    public List<Reporte> CntReporteVuelos() {
        return MdlReportePer.MdlReporteVuelo();
    }
}
