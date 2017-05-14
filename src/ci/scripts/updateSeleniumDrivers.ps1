#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#
#
# Powershell script updating Selenium drivers on ci server
#
# Author: Martin Stockhammer <martin_s@apache.org>  
# Date  : 2017-05-14
#
# Description:

param (
    [switch]$Verbose = $False,
    [switch]$Force = $False
)

$psVersion = $PSVersionTable.PSVersion

Write-Output "PS-Version: $psVersion"



$url = "http://selenium-release.storage.googleapis.com/2.53/IEDriverServer_x64_2.53.1.zip"
$downloadFile = "C:\jenkins\tools\iedriver\2.53.1\win64\IEDriverServer_x64_2.53.1.zip"
$downloadDir = Split-Path $downloadFile -Parent

if(!(Test-Path -Path $downloadDir )){
  New-Item -ItemType directory -Path $downloadFile

}
if ($Force -And (Test-Path -Path $downloadFile ) ) {
  Remove-Item $downloadFile
}

if ($Force -Or !(Test-Path -Path $downloadFile )){
  Invoke-WebRequest -Uri $url -OutFile $downloadFile

  $shell = New-Object -ComObject shell.application
  $zip = $shell.NameSpace($downloadFile)
  foreach ($item in $zip.items()) {
    $shell.Namespace($downloadDir).CopyHere($item)
  }
}

