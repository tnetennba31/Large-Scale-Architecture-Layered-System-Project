package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import commands.ExecuterForCommands;
import commands.FindIDByNameCmd;
import commands.GetAllAcidsCmd;
import commands.GetAllMetalsCmd;
import commands.OverwriteAcidToDissolveMetalCmd;

public class MetalGUI implements guiInterface {
	JPanel metalMainPanel = new JPanel();
	JPanel listOfMetalsPanel = new JPanel();
	JPanel metalControlPanel = new JPanel();
	
	public MetalGUI() {
		listOfMetalsPanel.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .2), FRAME_SIZE.height));
		metalControlPanel.setPreferredSize(new Dimension((int) Math.floor(FRAME_SIZE.height * .8), FRAME_SIZE.height));
		listOfMetalsPanel.setBackground(new Color(235, 91, 52));
		metalControlPanel.setBackground(new Color(52, 186, 235));
		metalMainPanel.setLayout(new BoxLayout(metalMainPanel, BoxLayout.LINE_AXIS));
		metalMainPanel.add(listOfMetalsPanel);
		metalMainPanel.add(metalControlPanel);
		
		setuplistOfMetalsPanel();
		setupupdateMetalPanel();
		setupfillerMetalPanel();
		setupoverWriteAcidToDissolveMetalPanel();
	}
	
	private void setuplistOfMetalsPanel() {
		listOfMetalsPanel.add(new JLabel("Metal"));
	}

	private void setupupdateMetalPanel() {
		JButton updateMetalButton = new JButton("Update");
		JPanel updateMetalPanel = new JPanel(new GridLayout(0,2));
		updateMetalPanel.add(new JLabel("Weight: "));
		JTextField weightInput = new JTextField();
		updateMetalPanel.add(weightInput);
		updateMetalPanel.add(new JLabel("Volume: "));
		JTextField volumeInput = new JTextField();
		updateMetalPanel.add(volumeInput);
		updateMetalPanel.add(updateMetalButton);
		
		updateMetalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * Command stuff goes here
				 * 
				 */
			}
		});
		
		metalControlPanel.add(updateMetalPanel);
	}
	
	private void setupfillerMetalPanel() {
		JPanel fillerMetalPanel = new JPanel(new GridLayout(0,2));
		
		metalControlPanel.add(fillerMetalPanel);
	}
	
	private void setupoverWriteAcidToDissolveMetalPanel() {
		JPanel overwriteAcidPanel = new JPanel(new GridLayout(0,2));
		overwriteAcidPanel.setBackground(new Color(235, 183, 52));
		overwriteAcidPanel.add(new JLabel("Overwrite Acid To Dissoolve Metal"));
		JButton overwriteButton = new JButton("Overwrite");
		overwriteAcidPanel.add(overwriteButton);
		overwriteAcidPanel.add(new JLabel("Select Metal"));
		ArrayList<String> metalNames = new ArrayList<String>();
		GetAllMetalsCmd metals = new GetAllMetalsCmd();
		try {
			new ExecuterForCommands(metals);
			metals.getMetals().forEach(n -> metalNames.add(n.getMetalName()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JComboBox metalNameBox = new JComboBox(metalNames.toArray());
		overwriteAcidPanel.add(metalNameBox);
		overwriteAcidPanel.add(new JLabel("Select new Acid"));
		ArrayList<String> acidNames = new ArrayList<String>();
		GetAllAcidsCmd acids = new GetAllAcidsCmd();
		try {
			new ExecuterForCommands(acids);
			acids.getAcids().forEach(n -> acidNames.add(n.getAcidName()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JComboBox acidNameBox = new JComboBox(acidNames.toArray());
		overwriteAcidPanel.add(acidNameBox);
		overwriteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindIDByNameCmd metalName = new FindIDByNameCmd(metalNameBox.getSelectedItem().toString());
				FindIDByNameCmd acidName = new FindIDByNameCmd(acidNameBox.getSelectedItem().toString());
				try {
					new ExecuterForCommands(metalName);
					new ExecuterForCommands(acidName);
					new ExecuterForCommands(new OverwriteAcidToDissolveMetalCmd(metalName.getID(), acidName.getID()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		metalControlPanel.add(overwriteAcidPanel);
	}
}
