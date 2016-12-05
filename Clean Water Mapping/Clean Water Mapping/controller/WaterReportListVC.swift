//
//  WaterReportListVC.swift
//  Clean Water Mapping
//
//  Created by Frances Tsenn on 12/4/16.
//  Copyright © 2016 Bits Please. All rights reserved.
//

import UIKit

class WaterReportListVC: UITableViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        tableView.register(UINib(nibName: "WaterReportCell", bundle: nil), forCellReuseIdentifier: "WaterReport")
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return Data.sharedInstance.waterReportList.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "WaterReport", for: indexPath) as! WaterReportCell
        let waterReport = Data.sharedInstance.waterReportList[indexPath.row]
        cell.reportLabel.text = "Report #\(waterReport.reportNumber)"
        let formatter = DateFormatter()
        formatter.dateStyle = DateFormatter.Style.short
        formatter.timeStyle = DateFormatter.Style.short
        cell.dateAndTimeLabel.text = formatter.string(from: waterReport.dateAndTime)
        cell.locationLabel.text = waterReport.location
        cell.typeLabel.text = waterReport.waterType
        cell.conditionLabel.text = waterReport.waterCondition
        return cell
    }
    
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 150
    }
}
