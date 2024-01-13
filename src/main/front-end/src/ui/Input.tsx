import {
  FieldError,
  FieldErrors,
  FieldValues,
  UseFormRegister,
  ValidationRule,
} from "react-hook-form";

interface InputProps {
  id: string;
  label: string;
  register: UseFormRegister<FieldValues>;
  errors: FieldErrors;
  type?: string;
  disabled?: boolean;
  required?: boolean;
  pattern?: ValidationRule<RegExp>;
}

const Input: React.FC<InputProps> = ({
  id,
  errors,
  label,
  register,
  disabled,
  required,
  pattern,
  type = "text",
}) => {
  return (
    <div className="w-full relative">
      <input
        id={id}
        disabled={disabled}
        {...register(id, { required, pattern })}
        placeholder=" "
        type={type}
        className={`
            peer
            w-full
            p-4
            pt-6
            font-light
            bg-white
            border-2
            rounded-md
            outline-none
            transition
            disabled:opacity-70
            disabled:cursor-not-allowed
            pl-4

            ${errors[id] ? "border-red-500" : "border-neutral-300"}
            ${errors[id] ? "focus:border-rose-500" : "focus:border-black"}
        `}
      />
      <label
        className={`
            absolute
            text-md
            
            transform
            -translate-y-3
            duration-150

            top-5
            z-10
            origin-[0]
            left-4
            peer-placeholder-shown:scale-100
            peer-placeholder-shown:translate-y-0
            peer-focus:scale-75
            peer-focus:-translate-y-4
            ${errors[id] ? "text-red-500" : "text-zinc-400"}
        `}
      >
        {label}
      </label>
    </div>
  );
};

export default Input;
