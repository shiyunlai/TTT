import { Component, OnInit } from '@angular/core';
import { _HttpClient } from '@delon/theme';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute} from '@angular/router';
import {appConfig} from '../../../../service/common';
import {UtilityService} from '../../../../service/utils.service';
@Component({
  selector: 'app-interurl',
  templateUrl: './interurl.component.html',
})
export class InterurlComponent implements OnInit {
    public orbitUrl: string;
    constructor(
        private http: _HttpClient,
        private utilityService: UtilityService,
        private sanitizer: DomSanitizer,
        public activatedRoute: ActivatedRoute
    ) {
        this.orbitUrl = 'http://106.15.103.14:18080/swagger-ui.html#/'; // url是http://:8080,即网页的服务器地址
     }

    appGuid: string;
    appName: string;
    ngOnInit() {
        this.activatedRoute.queryParams.subscribe(queryParams => {
            this.appGuid = queryParams.appGuid;
            this.getAppDetail(this.appGuid);
        });
    }

    getAppDetail(guid) {
        this.utilityService.getData(appConfig.testUrl + appConfig.API.acappAdd + '/' + guid)
            .subscribe(
                (val) => {
                    this.appName = val.result.appName;
                }
            );
    }

}
