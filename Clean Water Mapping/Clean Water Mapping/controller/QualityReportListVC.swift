//
//  QualityReportListVC.swift
//  Clean Water Mapping
//
//  Created by Frances Tsenn on 12/5/16.
//  Copyright © 2016 Bits Please. All rights reserved.
//

import UIKit

class QualityReportListVC: UITableViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        tableView.register(UINib(nibName: "QualityReportCell", bundle: nil), forCellReuseIdentifier: "QualityReport")
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return Data.sharedInstance.qualityReportList.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "QualityReport", for: indexPath) as! QualityReportCell
        let qualityReport = Data.sharedInstance.qualityReportList[indexPath.row]
        cell.reportLabel.text = "Report #\(qualityReport.reportNumber)"
        let formatter = DateFormatter()
        formatter.dateStyle = DateFormatter.Style.short
        formatter.timeStyle = DateFormatter.Style.short
        cell.dateAndTimeLabel.text = formatter.string(from: qualityReport.dateAndTime)
        cell.locationLabel.text = qualityReport.location
        cell.conditionLabel.text = qualityReport.overallCondition
        cell.virusPPMLabel.text = "\(qualityReport.virusPPM) PPM"
        cell.contaminantPPMLabel.text = "\(qualityReport.contaminantPPM) PPM"
        return cell
    }
    
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 150
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if (editingStyle == UITableViewCellEditingStyle.delete) {
            //remove from data
            Data.sharedInstance.qualityReportList.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: UITableViewRowAnimation.automatic)
        }
    }
}
