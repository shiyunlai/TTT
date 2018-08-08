// 功能module
export class FuncModule {
    // 当前页数
    public pi: number = 1;
    // 每业个数
    public size: number = 10;

    // 隶属应用
    public guidApp: string;

    // 功能类型
    public funcType: any;

    // 功能编号
    public funcCode: string;

    // 功能名称
    public funcName: string;

    // 功能描述
    public  funcDesc: string;

    // 是否启用
    public isopen: any;

    // 是否验证权限
    public  ischeck: any;

    // 父功能
    public  guidFunc: string;

    // 显示顺序
    public  displayOrder: number;
}
