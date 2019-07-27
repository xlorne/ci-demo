node {
  git 'https://github.com/1991wangliang/ci-demo.git'
  sh "mvn clean clover:setup test clover:aggregate clover:clover"
  step([
    $class: 'CloverPublisher',
    cloverReportDir: 'target/site',
    cloverReportFileName: 'clover.xml',
    healthyTarget: [methodCoverage: 70, conditionalCoverage: 80, statementCoverage: 80], // optional, default is: method=70, conditional=80, statement=80
    unhealthyTarget: [methodCoverage: 50, conditionalCoverage: 50, statementCoverage: 50], // optional, default is none
    failingTarget: [methodCoverage: 0, conditionalCoverage: 0, statementCoverage: 0]     // optional, default is none
  ])
}