package de.lutz.task;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.lutz.task.money.Money;

public class TestGUI extends JFrame {
	private static final long serialVersionUID = -7364625573532383423L;
	
	private JPanel jPanelInput;
	private JLabel jLabelValue;
	private JTextField jTextFieldValue;
	private JComboBox<String> jComboboxCountry;
	private DefaultComboBoxModel<String> comboboxModelCountry;
	private JButton jButtonCalc;
	
	private JPanel jPanelResult;
	private JLabel jLabelResult;
	
	private GuiController controller;
	
	public TestGUI(GuiController controller) {
		super("TEST");
		this.controller = controller;
		init();
	}

	private void init() {
		this.setLayout(new BorderLayout());
		this.add(getjPanelInput(), BorderLayout.CENTER);
		this.add(getjPanelResult(), BorderLayout.SOUTH);
	}
	
	private JPanel getjPanelInput() {
		if (this.jPanelInput == null) {
			this.jPanelInput = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
			this.jPanelInput.add(getjLabelValue());
			this.jPanelInput.add(getjTextFieldValue());
			this.jPanelInput.add(getjComboboxCountry());
			this.jPanelInput.add(getjButtonCalc());
		}

		return this.jPanelInput;
	}

	private JLabel getjLabelValue() {
		if (this.jLabelValue == null) {
			this.jLabelValue = new JLabel("Dayly Rate:");
		}

		return this.jLabelValue;
	}

	private JTextField getjTextFieldValue() {
		if (this.jTextFieldValue == null) {
			this.jTextFieldValue = new JTextField(30);
		}

		return this.jTextFieldValue;
	}

	private JComboBox<String> getjComboboxCountry() {
		if (this.jComboboxCountry == null) {
			this.jComboboxCountry = new JComboBox<>(getComboboxModelCountry());
		}

		return this.jComboboxCountry;
	}
	
	private DefaultComboBoxModel<String> getComboboxModelCountry() {
		if (comboboxModelCountry == null) {
			comboboxModelCountry = new DefaultComboBoxModel<>();
			for (String currency : this.controller.getCountries()) {
				this.comboboxModelCountry.addElement(currency);
			}
		}

		return comboboxModelCountry;
	}
	
	private JButton getjButtonCalc() {
		if (this.jButtonCalc == null) {
			this.jButtonCalc = new JButton("Calc");
			this.jButtonCalc.addActionListener(this::jButtonCalcActionPerformed);
		}

		return this.jButtonCalc;
	}
	
	private void jButtonCalcActionPerformed(ActionEvent l) {
		String text = this.getjTextFieldValue().getText();
		String[] parts = text.split(",");
		int denominator = Integer.parseInt(parts[0]);
		int cents = 0;
		if (parts.length == 2) {
			cents = Integer.parseInt(parts[1]);
		}
		Money money = new Money(denominator, cents);
		String countryCode = (String) getjComboboxCountry().getSelectedItem();
		Money income = this.controller.calculateMonthlyNetIncomeInZloty(money, countryCode);
		showIncome(income);
	}
	
	private JPanel getjPanelResult() {
		if (this.jPanelResult == null) {
			this.jPanelResult = new JPanel(new FlowLayout());
			this.jPanelResult.add(getjLabelResult());
			this.jPanelResult.setVisible(false);
		}

		return this.jPanelResult;
	}
	
	private JLabel getjLabelResult() {
		if (this.jLabelResult == null) {
			this.jLabelResult = new JLabel("");
		}

		return this.jLabelResult;
	}
	
	private void showIncome(Money money) {
		this.getjLabelResult().setText(money.toString());
		this.getjPanelResult().setVisible(true);
		this.pack();
	}
}
